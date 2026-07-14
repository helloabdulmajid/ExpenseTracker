package com.abdulmajid.expensetracker.service.impl;

import com.abdulmajid.expensetracker.dto.request.DebtRequest;
import com.abdulmajid.expensetracker.dto.response.DebtResponse;
import com.abdulmajid.expensetracker.exception.custom.DebtNotFoundException;
import com.abdulmajid.expensetracker.exception.custom.InvalidArgumentException;
import com.abdulmajid.expensetracker.exception.custom.UserNotFoundException;
import com.abdulmajid.expensetracker.model.Debt;
import com.abdulmajid.expensetracker.model.User;
import com.abdulmajid.expensetracker.repository.DebtRepository;
import com.abdulmajid.expensetracker.repository.UserRepository;
import com.abdulmajid.expensetracker.security.utils.SecurityUtils;
import com.abdulmajid.expensetracker.service.DebtService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor

public class DebtServiceImpl implements DebtService {

    private final DebtRepository debtRepository;

    private final UserRepository userRepository;

    @Override
    public DebtResponse createDebt(
            DebtRequest debtRequest
    ) {

        String email =
                SecurityUtils.getCurrentUserEmail();

        User user = userRepository
                .findByEmail(email)
                .orElseThrow(() ->
                        new UserNotFoundException(
                                "User not found"
                        )
                );

        Debt debt = new Debt();

        debt.setAmount(
                debtRequest.getAmount()
        );

        debt.setCreditor(
                debtRequest.getCreditor()
        );

        debt.setCreditorName(
                debtRequest.getCreditorName()
        );

        debt.setDebtor(
                debtRequest.getDebtor()
        );

        debt.setDebtorName(
                debtRequest.getDebtorName()
        );

        debt.setDueDate(
                debtRequest.getDueDate()
        );

        debt.setStatus(
                debtRequest.getStatus()
        );

        debt.setPriority(
                debtRequest.getPriority()
        );

        debt.setRecurring(
                debtRequest.getRecurring()
        );

        debt.setCategory(
                debtRequest.getCategory()
        );

        debt.setNote(
                debtRequest.getNote()
        );

        debt.setDate(
                debtRequest.getDate()
        );

        debt.setUser(user);

        Debt savedDebt =
                debtRepository.save(debt);

        return mapToResponse(savedDebt);
    }

    @Override
    public List<DebtResponse>
    getCurrentUserDebts() {

        String email =
                SecurityUtils.getCurrentUserEmail();

        User user = userRepository
                .findByEmail(email)
                .orElseThrow(() ->
                        new UserNotFoundException(
                                "User not found"
                        )
                );

        return debtRepository
                .findByUserId(user.getId())
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public DebtResponse getCurrentUserDebt(
            Integer debtId
    ) {

        String email =
                SecurityUtils.getCurrentUserEmail();

        User user = userRepository
                .findByEmail(email)
                .orElseThrow(() ->
                        new UserNotFoundException(
                                "User not found"
                        )
                );

        Debt debt = debtRepository
                .findById(debtId)
                .orElseThrow(() ->
                        new DebtNotFoundException(
                                "Debt not found"
                        )
                );

        if (!debt.getUser()
                .getId()
                .equals(user.getId())) {

            throw new InvalidArgumentException(
                    "You are not authorized to access this debt"
            );
        }

        return mapToResponse(debt);
    }

    @Override
    public DebtResponse updateDebt(

            Integer debtId,

            DebtRequest debtRequest
    ) {

        String email =
                SecurityUtils.getCurrentUserEmail();

        User user = userRepository
                .findByEmail(email)
                .orElseThrow(() ->
                        new UserNotFoundException(
                                "User not found"
                        )
                );

        Debt debt = debtRepository
                .findById(debtId)
                .orElseThrow(() ->
                        new DebtNotFoundException(
                                "Debt not found"
                        )
                );

        if (!debt.getUser()
                .getId()
                .equals(user.getId())) {

            throw new InvalidArgumentException(
                    "You are not authorized to update this debt"
            );
        }

        debt.setAmount(
                debtRequest.getAmount()
        );

        debt.setCreditor(
                debtRequest.getCreditor()
        );

        debt.setCreditorName(
                debtRequest.getCreditorName()
        );

        debt.setDebtor(
                debtRequest.getDebtor()
        );

        debt.setDebtorName(
                debtRequest.getDebtorName()
        );

        debt.setDueDate(
                debtRequest.getDueDate()
        );

        debt.setStatus(
                debtRequest.getStatus()
        );

        debt.setPriority(
                debtRequest.getPriority()
        );

        debt.setRecurring(
                debtRequest.getRecurring()
        );

        debt.setCategory(
                debtRequest.getCategory()
        );

        debt.setNote(
                debtRequest.getNote()
        );

        debt.setDate(
                debtRequest.getDate()
        );
        Debt updatedDebt =
                debtRepository.save(debt);

        return mapToResponse(updatedDebt);
    }

    @Override
    public DebtResponse deleteDebt(
            Integer debtId
    ) {

        String email =
                SecurityUtils.getCurrentUserEmail();

        User user = userRepository
                .findByEmail(email)
                .orElseThrow(() ->
                        new UserNotFoundException(
                                "User not found"
                        )
                );

        Debt debt = debtRepository
                .findById(debtId)
                .orElseThrow(() ->
                        new DebtNotFoundException(
                                "Debt not found"
                        )
                );

        if (!debt.getUser()
                .getId()
                .equals(user.getId())) {

            throw new InvalidArgumentException(
                    "You are not authorized to delete this debt"
            );
        }

        DebtResponse response =
                mapToResponse(debt);

        debtRepository.delete(debt);

        return response;
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