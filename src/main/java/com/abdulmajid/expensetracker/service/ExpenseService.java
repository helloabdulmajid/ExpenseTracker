package com.abdulmajid.expensetracker.service;

import com.abdulmajid.expensetracker.dto.ExpenseRequest;
import com.abdulmajid.expensetracker.dto.ExpenseResponse;
import com.abdulmajid.expensetracker.model.Expense;

import java.util.List;

public interface ExpenseService {
    public ExpenseResponse createExpense(Integer userId, ExpenseRequest expenseRequest);

    List<Expense> getExpenseForUser(Integer userId);

    ExpenseResponse getExpense(Integer expenseId);

    List<ExpenseResponse> getAllExpense();

    String updateExpense(Integer userId, Integer expenseId, ExpenseRequest expenseRequest);

    String deleteExpense(Integer userId, Integer expenseId);
}
