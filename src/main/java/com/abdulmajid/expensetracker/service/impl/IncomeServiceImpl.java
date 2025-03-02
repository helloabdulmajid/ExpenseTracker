package com.abdulmajid.expensetracker.service.impl;

import com.abdulmajid.expensetracker.dto.IncomeCategoryResponse;
import com.abdulmajid.expensetracker.dto.IncomeRequest;
import com.abdulmajid.expensetracker.dto.IncomeResponse;
import com.abdulmajid.expensetracker.exception.custom.CategoryNotFoundException;
import com.abdulmajid.expensetracker.exception.custom.IncomeNotFoundException;
import com.abdulmajid.expensetracker.exception.custom.UserNotFoundException;
import com.abdulmajid.expensetracker.model.Income;
import com.abdulmajid.expensetracker.model.IncomeCategory;
import com.abdulmajid.expensetracker.model.User;
import com.abdulmajid.expensetracker.repository.ExpenseCategoryRepository;
import com.abdulmajid.expensetracker.repository.IncomeCategoryRepository;
import com.abdulmajid.expensetracker.repository.IncomeRepository;
import com.abdulmajid.expensetracker.repository.UserRepository;
import com.abdulmajid.expensetracker.service.IncomeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class IncomeServiceImpl implements IncomeService {
    @Autowired
    private IncomeRepository incomeRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ExpenseCategoryRepository expenseCategoryRepository;

    @Autowired
    private IncomeCategoryRepository incomeCategoryRepository;

    @Override
    public IncomeResponse createIncomeForUser(@Valid Integer userId, IncomeRequest incomeRequest) {

        // get user from userId(param)
        User existsUser = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + userId));


        //get category from id
        IncomeCategory dbIncomeCategory = incomeCategoryRepository.findById(incomeRequest.getCategoryId())
                .orElseThrow(() -> new CategoryNotFoundException("Income Category not found "));


        Income income = new Income(incomeRequest.getAmount(), incomeRequest.getReceiveMode(),
                incomeRequest.getNote(), incomeRequest.getDay(),
                incomeRequest.getDate(), dbIncomeCategory, existsUser);

        Income saveIncome = incomeRepository.save(income);

        //get Total income from user table
        BigDecimal oldTotalIncome = existsUser.getIncome();
        BigDecimal newTotalIncome = oldTotalIncome.add(incomeRequest.getAmount());

        //add newTotalIncome in user table

        existsUser.setIncome(newTotalIncome);
        existsUser.setUpdatedAt(new Date());

        userRepository.save(existsUser);
        IncomeCategoryResponse incomeCategoryResponse = new IncomeCategoryResponse(dbIncomeCategory.getId(),
                dbIncomeCategory.getCategoryName(), dbIncomeCategory.isDefaultCategory());

        return new IncomeResponse(saveIncome.getId(), saveIncome.getAmount(),
                saveIncome.getReceiveMode(), saveIncome.getNote(),
                saveIncome.getDay(), saveIncome.getDate(), saveIncome.getCreatedAt(),
                saveIncome.getUpdatedAt(), incomeCategoryResponse, saveIncome.getUser());

    }

    @Override
    public List<IncomeResponse> getAllIncomeForUser(Integer userId) {

        // get user from userId(param)
        User existsUser = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + userId));

        List<Income> userIncomeList = incomeRepository.findByUserId(userId);

        ArrayList<IncomeResponse> incomeResponses = new ArrayList<>();
        for (Income income : userIncomeList) {
            IncomeResponse incomeResponse = new IncomeResponse(income.getId(), income.getAmount(),
                    income.getReceiveMode(), income.getNote(), income.getDay(), income.getDate(),
                    income.getCreatedAt(), income.getUpdatedAt(),
                    new IncomeCategoryResponse(income.getIncomeCategory().getId(),
                            income.getIncomeCategory().getCategoryName(),
                            income.getIncomeCategory().isDefaultCategory()));
            incomeResponses.add(incomeResponse);
        }
        return incomeResponses;
    }

    @Override
    public IncomeResponse getIncome(Integer incomeId) {
        Income existsIncome = incomeRepository.findById(incomeId)
                .orElseThrow(() -> new IncomeNotFoundException("Income Not found with Income Id:  " + incomeId));

        //get category from existsIncome
        IncomeCategory dbIncomeCategory = incomeCategoryRepository.findById(existsIncome.getIncomeCategory().getId())
                .orElseThrow(() -> new CategoryNotFoundException("Income Category not found "));

        IncomeCategoryResponse incomeCategoryResponse = new IncomeCategoryResponse(dbIncomeCategory.getId(),
                dbIncomeCategory.getCategoryName(), dbIncomeCategory.isDefaultCategory());

        return new IncomeResponse(existsIncome.getId(), existsIncome.getAmount(),
                existsIncome.getReceiveMode(), existsIncome.getNote(),
                existsIncome.getDay(), existsIncome.getDate(), existsIncome.getCreatedAt(),
                existsIncome.getUpdatedAt(), incomeCategoryResponse, existsIncome.getUser());

    }

    @Override
    public List<IncomeResponse> getAllIncome() {
        List<Income> allIncome = incomeRepository.findAll();
        ArrayList<IncomeResponse> incomeResponses = new ArrayList<>();
        for (Income income : allIncome) {
            IncomeResponse incomeResponse = new IncomeResponse(income.getId(), income.getAmount(),
                    income.getReceiveMode(), income.getNote(), income.getDay(), income.getDate(),
                    income.getCreatedAt(), income.getUpdatedAt(),
                    new IncomeCategoryResponse(income.getIncomeCategory().getId(),
                            income.getIncomeCategory().getCategoryName(),
                            income.getIncomeCategory().isDefaultCategory()));
            incomeResponses.add(incomeResponse);
        }
        return incomeResponses;
    }


    @Override
    public String updateIncome(Integer userId, Integer incomeId, IncomeRequest incomeRequest) {

        // get user from userId(param)
        User existsUser = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + userId));


        //get income from incomeId
        Income existsIncome = incomeRepository.findById(incomeId)
                .orElseThrow(() -> new IncomeNotFoundException("Income Not found with Income Id:  " + incomeId));

        BigDecimal oldAmount = existsIncome.getAmount();
        BigDecimal newAmount = incomeRequest.getAmount();

        IncomeCategory dbIncomeCategory = incomeCategoryRepository.findById(existsIncome.getIncomeCategory().getId())
                .orElseThrow(() -> new CategoryNotFoundException("Income Category not found "));


        // If new amount is the same as old, just update the income all records and return
        if (newAmount.compareTo(oldAmount) == 0) {
            existsIncome.setIncomeCategory(dbIncomeCategory);
            existsIncome.setNote(incomeRequest.getNote());
            existsIncome.setUpdatedAt(new Date());

            incomeRepository.save(existsIncome);
            return "Income Updated SuccessFully , Income Id :" + incomeId; //  Exit the function early (no need to update user's income)
        }

        // if NewAmount is greater than old Amount → Add the difference to user table

        if (newAmount.compareTo(oldAmount) > 0) {
            BigDecimal difference = newAmount.subtract(oldAmount);
            existsUser.setIncome(existsUser.getIncome().add(difference));
        } else {
            // if New amount is smaller → Subtract the difference
            BigDecimal difference = oldAmount.subtract(newAmount);

            if (existsUser.getIncome().subtract(difference).compareTo(BigDecimal.ZERO) < 0) {
                throw new IncomeNotFoundException("Not enough balance to update this income");
            }

            existsUser.setIncome(existsUser.getIncome().subtract(difference));
        }

        // Update the income record
        existsIncome.setAmount(newAmount);
        existsIncome.setIncomeCategory(dbIncomeCategory);
        existsIncome.setNote(incomeRequest.getNote());
        existsIncome.setUpdatedAt(new Date());

        // Save updates
        incomeRepository.save(existsIncome);
        existsUser.setUpdatedAt(new Date());
        userRepository.save(existsUser);

        return "Income Updated SuccessFully , Income Id :" + incomeId;
    }

    @Override
    public String deleteIncome(Integer userId, Integer incomeId) {

        User existsUser = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + userId));

        Income existsIncome = incomeRepository.findById(incomeId)
                .orElseThrow(() -> new IncomeNotFoundException("Income Not found with Income Id:  " + incomeId));

        BigDecimal incomeAmount = existsIncome.getAmount();

        try {
            existsUser.setIncome(existsUser.getIncome().subtract(incomeAmount));

            // Remove income record
            incomeRepository.delete(existsIncome);
            userRepository.save(existsUser);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Deleted Income Id : -> " + incomeId;
    }


}
