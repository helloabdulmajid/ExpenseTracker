package com.abdulmajid.expensetracker.service;

import com.abdulmajid.expensetracker.dto.request.DebtRequest;
import com.abdulmajid.expensetracker.dto.response.DebtResponse;

import java.util.List;

public interface DebtService {

    DebtResponse createDebt(
            DebtRequest debtRequest
    );

    List<DebtResponse> getCurrentUserDebts();

    DebtResponse getCurrentUserDebt(
            Integer debtId
    );

    DebtResponse updateDebt(
            Integer debtId,
            DebtRequest debtRequest
    );

    DebtResponse deleteDebt(
            Integer debtId
    );
}