package com.abdulmajid.expensetracker.service;

import com.abdulmajid.expensetracker.dto.request.ExpenseRequest;
import com.abdulmajid.expensetracker.dto.response.ExpenseResponse;
import org.springframework.data.domain.Page;

import java.time.LocalDate;
import java.util.List;

public interface ExpenseService {

    List<ExpenseResponse> getCurrentUserExpenses();

    ExpenseResponse getCurrentUserExpense(
            Integer expenseId
    );

    ExpenseResponse createExpense(
            ExpenseRequest expenseRequest
    );

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
            Integer expenseId,
            ExpenseRequest expenseRequest
    );

    ExpenseResponse updateExpense(

            Integer userId,

            Integer expenseId,

            ExpenseRequest expenseRequest
    );

    ExpenseResponse deleteExpense(
            Integer expenseId
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

    Page<ExpenseResponse> getCurrentUserExpenses(

            int page,

            int size,

            String sortBy,

            String sortDir
    );
}