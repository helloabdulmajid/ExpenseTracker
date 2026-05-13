package com.abdulmajid.expensetracker.controller;

import com.abdulmajid.expensetracker.dto.response.ExpenseResponse;
import com.abdulmajid.expensetracker.service.ExpenseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/expenses")

@RequiredArgsConstructor

public class AuthenticatedExpenseController {

    private final ExpenseService expenseService;

    // GET CURRENT USER EXPENSES
    @GetMapping("/me")
    public ResponseEntity<List<ExpenseResponse>>
    getCurrentUserExpenses() {

        return ResponseEntity.ok(

                expenseService
                        .getCurrentUserExpenses()
        );
    }

    // GET SINGLE CURRENT USER EXPENSE
    @GetMapping("/me/{expenseId}")
    public ResponseEntity<ExpenseResponse>
    getCurrentUserExpense(

            @PathVariable Integer expenseId
    ) {

        return ResponseEntity.ok(

                expenseService
                        .getCurrentUserExpense(
                                expenseId
                        )
        );
    }
}