package com.abdulmajid.expensetracker.service;

import com.abdulmajid.expensetracker.dto.request.DebtRequest;
import com.abdulmajid.expensetracker.dto.response.DebtResponse;

import java.util.List;

public interface DebtService {

    DebtResponse createDebtForUser(
            Integer userId,
            DebtRequest debtRequest
    );

    List<DebtResponse> getAllDebtForUser(
            Integer userId
    );

    DebtResponse getDebt(
            Integer userId,
            Integer debtId
    );

    DebtResponse updateDebt(
            Integer userId,
            Integer debtId,
            DebtRequest debtRequest
    );

    void deleteDebt(
            Integer userId,
            Integer debtId
    );
}