package com.abdulmajid.expensetracker.controller;

import com.abdulmajid.expensetracker.dto.request.ExpenseRequest;
import com.abdulmajid.expensetracker.dto.response.ExpenseResponse;
import com.abdulmajid.expensetracker.enums.PaymentMode;
import com.abdulmajid.expensetracker.service.ExpenseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    // GET CURRENT USER EXPENSES WITH PAGINATION
    @GetMapping("/me")
    public ResponseEntity<Page<ExpenseResponse>>
    getCurrentUserExpenses(

            @RequestParam(
                    defaultValue = "0"
            )
            int page,

            @RequestParam(
                    defaultValue = "5"
            )
            int size,

            @RequestParam(
                    defaultValue = "date"
            )
            String sortBy,

            @RequestParam(
                    defaultValue = "desc"
            )
            String sortDir,

            @RequestParam(required = false)
            PaymentMode paymentMode,

            @RequestParam(
                    required = false
            )
            String keyword
    ) {

        return ResponseEntity.ok(

                expenseService
                        .getCurrentUserExpenses(

                                page,

                                size,

                                sortBy,

                                sortDir,

                                paymentMode,

                                keyword
                        )
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

    @PutMapping("/{expenseId}")
    public ResponseEntity<ExpenseResponse>
    updateExpense(

            @PathVariable Integer expenseId,

            @Valid
            @RequestBody
            ExpenseRequest expenseRequest
    ) {

        return ResponseEntity.ok(

                expenseService.updateExpense(
                        expenseId,
                        expenseRequest
                )
        );
    }

    @DeleteMapping("/{expenseId}")
    public ResponseEntity<ExpenseResponse>
    deleteExpense(

            @PathVariable Integer expenseId
    ) {

        return ResponseEntity.ok(

                expenseService.deleteExpense(
                        expenseId
                )
        );
    }
}