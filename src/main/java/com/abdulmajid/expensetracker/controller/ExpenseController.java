package com.abdulmajid.expensetracker.controller;

import com.abdulmajid.expensetracker.dto.request.ExpenseRequest;
import com.abdulmajid.expensetracker.dto.response.ExpenseResponse;
import com.abdulmajid.expensetracker.service.ExpenseService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/users/{userId}/expenses")
@RequiredArgsConstructor
@Tag(
        name = "Expense APIs",
        description = "Manage user expenses"
)
public class ExpenseController {

    private final ExpenseService expenseService;

    // CREATE EXPENSE
    @PostMapping
    public ResponseEntity<ExpenseResponse> createExpense(

            @PathVariable Integer userId,

            @Valid @RequestBody ExpenseRequest expenseRequest
    ) {

        ExpenseResponse response =
                expenseService.createExpense(userId, expenseRequest);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    // jwt authenticated user's single expense only.

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

    // jwt user list of authenticated user's all expenses.

    @GetMapping("/me")
    public ResponseEntity<List<ExpenseResponse>>
    getCurrentUserExpenses() {

        return ResponseEntity.ok(
                expenseService.getCurrentUserExpenses()
        );
    }

    // GET ALL EXPENSES FOR A USER
    @GetMapping
    public ResponseEntity<List<ExpenseResponse>> getUserExpenses(

            @PathVariable Integer userId
    ) {

        List<ExpenseResponse> response =
                expenseService.getExpenseForUser(userId);

        return ResponseEntity.ok(response);
    }

    // GET SINGLE EXPENSE
    @GetMapping("/{expenseId}")
    public ResponseEntity<ExpenseResponse> getExpense(

            @PathVariable Integer userId,

            @PathVariable Integer expenseId
    ) {

        ExpenseResponse response =
                expenseService.getExpense(userId, expenseId);

        return ResponseEntity.ok(response);
    }

    // UPDATE EXPENSE
    @PutMapping("/{expenseId}")
    public ResponseEntity<ExpenseResponse> updateExpense(

            @PathVariable Integer userId,

            @PathVariable Integer expenseId,

            @Valid @RequestBody ExpenseRequest expenseRequest
    ) {

        ExpenseResponse response =
                expenseService.updateExpense(
                        userId,
                        expenseId,
                        expenseRequest
                );

        return ResponseEntity.ok(response);
    }

    // DELETE EXPENSE
    @DeleteMapping("/{expenseId}")
    public ResponseEntity<Void> deleteExpense(

            @PathVariable Integer userId,

            @PathVariable Integer expenseId
    ) {

        expenseService.deleteExpense(userId, expenseId);

        return ResponseEntity.noContent().build();
    }

    // GET MONTHLY EXPENSES
    @GetMapping("/monthly/{month}")
    public ResponseEntity<List<ExpenseResponse>> getMonthlyExpenses(

            @PathVariable Integer userId,

            @PathVariable Integer month
    ) {

        List<ExpenseResponse> response =
                expenseService.getMonthlyExpenses(userId, month);

        return ResponseEntity.ok(response);
    }

    // GET WEEKLY EXPENSES
    @GetMapping("/weekly/{week}")
    public ResponseEntity<List<ExpenseResponse>> getWeeklyExpenses(

            @PathVariable Integer userId,

            @PathVariable Integer week
    ) {

        List<ExpenseResponse> response =
                expenseService.getWeeklyExpenses(userId, week);

        return ResponseEntity.ok(response);
    }

    // GET DAILY EXPENSES
    @GetMapping("/daily")
    public ResponseEntity<List<ExpenseResponse>> getDailyExpenses(

            @PathVariable Integer userId,

            @RequestParam
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate date
    ) {

        List<ExpenseResponse> response =
                expenseService.getDailyExpenses(userId, date);

        return ResponseEntity.ok(response);
    }
}