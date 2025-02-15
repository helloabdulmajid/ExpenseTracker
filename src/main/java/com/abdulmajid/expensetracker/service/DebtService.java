package com.abdulmajid.expensetracker.service;

import com.abdulmajid.expensetracker.dto.DebtRequest;
import com.abdulmajid.expensetracker.dto.DebtResponse;

import java.util.List;

public interface DebtService {
    DebtResponse createDebtForUser(Integer userId, DebtRequest debtRequest);

    List<DebtResponse> getAllDebtForUser(Integer userId);

    DebtResponse getDebt(Integer debtId);

    List<DebtResponse> getAllDebt();

    String updateDebt(Integer userId, Integer debtId, DebtRequest debtRequest);

    String deleteDebt(Integer userId, Integer debtId);
}
