package com.abdulmajid.expensetracker.service;

import com.abdulmajid.expensetracker.dto.request.ExpenseRequest;
import com.abdulmajid.expensetracker.dto.response.ExpenseResponse;

import java.time.LocalDate;
import java.util.List;

public interface ExpenseService {

    ExpenseResponse createExpense(
            Integer userId,
            ExpenseRequest expenseRequest
    );

    List<ExpenseResponse> getExpenseForUser(
            Integer userId
    );

    ExpenseResponse getExpense(

            Integer userId,

            Integer expenseId
    );

    List<ExpenseResponse> getAllExpense();

    ExpenseResponse updateExpense(

            Integer userId,

            Integer expenseId,

            ExpenseRequest expenseRequest
    );

    void deleteExpense(

            Integer userId,

            Integer expenseId
    );

    List<ExpenseResponse> getMonthlyExpenses(

            Integer userId,

            Integer month
    );

    List<ExpenseResponse> getWeeklyExpenses(

            Integer userId,

            Integer week
    );

    List<ExpenseResponse> getDailyExpenses(

            Integer userId,

            LocalDate day
    );
}