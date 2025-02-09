package com.abdulmajid.expensetracker.service;

import com.abdulmajid.expensetracker.dto.DebtRequest;
import com.abdulmajid.expensetracker.dto.DebtResponse;

import java.util.List;

public interface DebtService {
    DebtResponse createDebtForUser(Integer userId, DebtRequest debtRequest);

    List<DebtResponse> getAllDebtForUser(Integer userId);
}
