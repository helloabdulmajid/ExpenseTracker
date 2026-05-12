package com.abdulmajid.expensetracker.service.impl;

import com.abdulmajid.expensetracker.dto.request.IncomeRequest;
import com.abdulmajid.expensetracker.dto.response.IncomeResponse;
import com.abdulmajid.expensetracker.exception.custom.CategoryNotFoundException;
import com.abdulmajid.expensetracker.exception.custom.IncomeNotFoundException;
import com.abdulmajid.expensetracker.exception.custom.UserNotFoundException;
import com.abdulmajid.expensetracker.model.Income;
import com.abdulmajid.expensetracker.model.IncomeCategory;
import com.abdulmajid.expensetracker.model.User;
import com.abdulmajid.expensetracker.repository.IncomeCategoryRepository;
import com.abdulmajid.expensetracker.repository.IncomeRepository;
import com.abdulmajid.expensetracker.repository.UserRepository;
import com.abdulmajid.expensetracker.service.IncomeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor

public class IncomeServiceImpl implements IncomeService {

    private final IncomeRepository incomeRepository;

    private final UserRepository userRepository;

    private final IncomeCategoryRepository incomeCategoryRepository;

    // CREATE INCOME
    @Override
    public IncomeResponse createIncomeForUser(Integer userId, IncomeRequest incomeRequest) {

        User user = getUserById(userId);

        IncomeCategory incomeCategory = getIncomeCategoryById(incomeRequest.getCategoryId());

        Income income = new Income(
                incomeRequest.getAmount(),
                incomeRequest.getReceiveMode(),
                incomeRequest.getNote(),
                incomeRequest.getDate(),
                incomeCategory,
                user
        );

        Income savedIncome = incomeRepository.save(income);

        return mapToResponse(savedIncome);
    }

    // GET ALL USER INCOMES
    @Override
    public List<IncomeResponse> getAllIncomeForUser(
            Integer userId
    ) {

        getUserById(userId);

        return incomeRepository.findByUserId(userId)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    // GET SINGLE INCOME
    @Override
    public IncomeResponse getIncome(

            Integer userId,

            Integer incomeId
    ) {

        Income income = getIncomeByIdAndUserId(
                incomeId,
                userId
        );

        return mapToResponse(income);
    }

    // GET ALL INCOMES
    @Override
    public List<IncomeResponse> getAllIncome() {

        return incomeRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    // UPDATE INCOME
    @Override
    public IncomeResponse updateIncome(

            Integer userId,

            Integer incomeId,

            IncomeRequest incomeRequest
    ) {

        Income income = getIncomeByIdAndUserId(
                incomeId,
                userId
        );

        IncomeCategory incomeCategory =
                getIncomeCategoryById(
                        incomeRequest.getCategoryId()
                );

        income.setAmount(
                incomeRequest.getAmount()
        );

        income.setReceiveMode(
                incomeRequest.getReceiveMode()
        );

        income.setNote(
                incomeRequest.getNote()
        );

        income.setDate(
                incomeRequest.getDate()
        );

        income.setIncomeCategory(
                incomeCategory
        );

        Income updatedIncome =
                incomeRepository.save(income);

        return mapToResponse(updatedIncome);
    }

    // DELETE INCOME
    @Override
    public void deleteIncome(

            Integer userId,

            Integer incomeId
    ) {

        Income income = getIncomeByIdAndUserId(
                incomeId,
                userId
        );

        incomeRepository.delete(income);
    }

    // HELPER METHOD
    private User getUserById(Integer userId) {

        return userRepository.findById(userId)
                .orElseThrow(() ->
                        new UserNotFoundException(
                                "User not found with ID: " + userId
                        )
                );
    }

    // HELPER METHOD
    private IncomeCategory getIncomeCategoryById(Integer categoryId) {

        return incomeCategoryRepository.findById(categoryId)
                .orElseThrow(() ->
                        new CategoryNotFoundException(
                                "Income category not found with ID: "
                                        + categoryId
                        )
                );
    }

    // HELPER METHOD
    private Income getIncomeByIdAndUserId(Integer incomeId, Integer userId) {

        return incomeRepository.findByIdAndUserId(
                        incomeId,
                        userId
                )
                .orElseThrow(() ->
                        new IncomeNotFoundException(
                                "Income not found with ID: "
                                        + incomeId
                        )
                );
    }


    // MAPPER METHOD
    private IncomeResponse mapToResponse(Income income) {

        IncomeResponse response = new IncomeResponse();

        response.setId(
                income.getId()
        );

        response.setAmount(
                income.getAmount()
        );

        response.setReceiveMode(
                income.getReceiveMode()
        );

        response.setNote(
                income.getNote()
        );

        response.setDate(
                income.getDate()
        );

        response.setCategoryId(
                income.getIncomeCategory().getId()
        );

        response.setCategoryName(
                income.getIncomeCategory().getCategoryName()
        );

        response.setUserId(
                income.getUser().getId()
        );

        response.setCreatedAt(
                income.getCreatedAt()
        );

        response.setUpdatedAt(
                income.getUpdatedAt()
        );

        return response;
    }
}