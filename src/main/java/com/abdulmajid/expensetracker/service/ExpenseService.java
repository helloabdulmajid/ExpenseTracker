package com.abdulmajid.expensetracker.service;

import com.abdulmajid.expensetracker.dto.request.ExpenseRequest;
import com.abdulmajid.expensetracker.dto.response.DashboardSummaryResponse;
import com.abdulmajid.expensetracker.dto.response.ExpenseResponse;
import com.abdulmajid.expensetracker.enums.PaymentMode;
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

    ExpenseResponse updateExpense(
            Integer expenseId,
            ExpenseRequest expenseRequest
    );

    ExpenseResponse deleteExpense(
            Integer expenseId
    );

    Page<ExpenseResponse> getCurrentUserExpenses(

            int page,

            int size,

            String sortBy,

            String sortDir,

            PaymentMode paymentMode,

            String keyword
    );

    DashboardSummaryResponse
    getDashboardSummary();
}