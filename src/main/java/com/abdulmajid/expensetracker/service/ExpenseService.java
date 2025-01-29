package com.abdulmajid.expensetracker.service;

import com.abdulmajid.expensetracker.dto.ExpenseRequest;
import com.abdulmajid.expensetracker.dto.ExpenseResponse;

public interface ExpenseService {
    public ExpenseResponse createExpense(Integer userId, ExpenseRequest expenseRequest);
}
