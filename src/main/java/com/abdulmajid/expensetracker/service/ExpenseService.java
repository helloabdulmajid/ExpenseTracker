package com.abdulmajid.expensetracker.service;

import com.abdulmajid.expensetracker.dto.ExpenseRequest;
import com.abdulmajid.expensetracker.dto.ExpenseResponse;

import java.time.LocalDate;
import java.util.List;

public interface ExpenseService {
    public ExpenseResponse createExpense(Integer userId, ExpenseRequest expenseRequest);

    List<ExpenseResponse> getExpenseForUser(Integer userId);

    ExpenseResponse getExpense(Integer expenseId);

    List<ExpenseResponse> getAllExpense();

    String updateExpense(Integer userId, Integer expenseId, ExpenseRequest expenseRequest);

    String deleteExpense(Integer userId, Integer expenseId);

    List<ExpenseResponse> getMonthlyExpenses(Integer userId, Integer month);

    List<ExpenseResponse> getWeeklyExpenses(Integer userId, Integer week);

    List<ExpenseResponse> getDailyExpenses(Integer userId, LocalDate day);
}
