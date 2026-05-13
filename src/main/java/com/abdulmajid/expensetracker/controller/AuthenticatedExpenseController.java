package com.abdulmajid.expensetracker.controller;

import com.abdulmajid.expensetracker.dto.request.ExpenseRequest;
import com.abdulmajid.expensetracker.dto.response.ExpenseResponse;
import com.abdulmajid.expensetracker.service.ExpenseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/expenses")

@RequiredArgsConstructor

public class AuthenticatedExpenseController {

    private final ExpenseService expenseService;

    //Create Expense without user id

    @PostMapping
    public ResponseEntity<ExpenseResponse>
    createExpense(

            @Valid
            @RequestBody
            ExpenseRequest expenseRequest
    ) {

        return ResponseEntity.status(HttpStatus.CREATED)

                .body(

                        expenseService
                                .createExpense(
                                        expenseRequest
                                )
                );
    }

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