package com.abdulmajid.expensetracker.service.impl;

import com.abdulmajid.expensetracker.dto.request.DebtRequest;
import com.abdulmajid.expensetracker.dto.response.DebtResponse;
import com.abdulmajid.expensetracker.exception.custom.DebtNotFoundException;
import com.abdulmajid.expensetracker.exception.custom.UserNotFoundException;
import com.abdulmajid.expensetracker.model.Debt;
import com.abdulmajid.expensetracker.model.User;
import com.abdulmajid.expensetracker.repository.DebtRepository;
import com.abdulmajid.expensetracker.repository.UserRepository;
import com.abdulmajid.expensetracker.service.DebtService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor

public class DebtServiceImpl implements DebtService {

    private final DebtRepository debtRepository;

    private final UserRepository userRepository;

    // CREATE DEBT
    @Override
    public DebtResponse createDebtForUser(

            Integer userId,

            DebtRequest debtRequest
    ) {

        User user = getUserById(userId);

        Debt debt = new Debt(
                debtRequest.getAmount(),
                debtRequest.getCreditor(),
                debtRequest.getCreditorName(),
                debtRequest.getDebtor(),
                debtRequest.getDebtorName(),
                debtRequest.getDueDate(),
                debtRequest.getStatus(),
                debtRequest.getPriority(),
                debtRequest.getRecurring(),
                debtRequest.getCategory(),
                debtRequest.getNote(),
                debtRequest.getDate(),
                user
        );

        Debt savedDebt = debtRepository.save(debt);

        return mapToResponse(savedDebt);
    }

    // GET ALL USER DEBTS
    @Override
    public List<DebtResponse> getAllDebtForUser(
            Integer userId
    ) {

        getUserById(userId);

        return debtRepository.findByUserId(userId)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    // GET SINGLE DEBT
    @Override
    public DebtResponse getDebt(

            Integer userId,

            Integer debtId
    ) {

        Debt debt = getDebtByIdAndUserId(
                debtId,
                userId
        );

        return mapToResponse(debt);
    }

    // UPDATE DEBT
    @Override
    public DebtResponse updateDebt(

            Integer userId,

            Integer debtId,

            DebtRequest debtRequest
    ) {

        Debt debt = getDebtByIdAndUserId(
                debtId,
                userId
        );

        debt.setAmount(debtRequest.getAmount());
        debt.setCreditor(debtRequest.getCreditor());
        debt.setCreditorName(debtRequest.getCreditorName());
        debt.setDebtor(debtRequest.getDebtor());
        debt.setDebtorName(debtRequest.getDebtorName());
        debt.setDueDate(debtRequest.getDueDate());
        debt.setStatus(debtRequest.getStatus());
        debt.setPriority(debtRequest.getPriority());
        debt.setRecurring(debtRequest.getRecurring());
        debt.setCategory(debtRequest.getCategory());
        debt.setNote(debtRequest.getNote());
        debt.setDate(debtRequest.getDate());

        Debt updatedDebt = debtRepository.save(debt);

        return mapToResponse(updatedDebt);
    }

    // DELETE DEBT
    @Override
    public void deleteDebt(

            Integer userId,

            Integer debtId
    ) {

        Debt debt = getDebtByIdAndUserId(
                debtId,
                userId
        );

        debtRepository.delete(debt);
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
    private Debt getDebtByIdAndUserId(

            Integer debtId,

            Integer userId
    ) {

        return debtRepository.findByIdAndUserId(
                        debtId,
                        userId
                )
                .orElseThrow(() ->
                        new DebtNotFoundException(
                                "Debt not found with ID: " + debtId
                        )
                );
    }

    // MAPPER METHOD
    private DebtResponse mapToResponse(Debt debt) {

        DebtResponse response = new DebtResponse();

        response.setId(debt.getId());
        response.setAmount(debt.getAmount());
        response.setCreditor(debt.getCreditor());
        response.setCreditorName(debt.getCreditorName());
        response.setDebtor(debt.getDebtor());
        response.setDebtorName(debt.getDebtorName());
        response.setDueDate(debt.getDueDate());
        response.setStatus(debt.getStatus());
        response.setPriority(debt.getPriority());
        response.setRecurring(debt.getRecurring());
        response.setCategory(debt.getCategory());
        response.setNote(debt.getNote());
        response.setDate(debt.getDate());

        response.setUserId(
                debt.getUser().getId()
        );

        response.setCreatedAt(
                debt.getCreatedAt()
        );

        response.setUpdatedAt(
                debt.getUpdatedAt()
        );

        return response;
    }
}