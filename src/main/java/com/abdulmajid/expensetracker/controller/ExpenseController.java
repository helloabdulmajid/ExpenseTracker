package com.abdulmajid.expensetracker.controller;

import com.abdulmajid.expensetracker.dto.ExpenseRequest;
import com.abdulmajid.expensetracker.dto.ExpenseResponse;
import com.abdulmajid.expensetracker.service.ExpenseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("expense")
public class ExpenseController {
    @Autowired
    private ExpenseService expenseService;

    @PostMapping("/create/{userId}")
    public ExpenseResponse createExpense(@PathVariable Integer userId,
                                         @RequestBody @Valid ExpenseRequest expenseRequest) {


        return expenseService.createExpense(userId, expenseRequest);
    }

    @GetMapping("/user/{userId}")
    public List<ExpenseResponse> getExpenseForUser(@PathVariable Integer userId) {
        return expenseService.getExpenseForUser(userId);
    }

    @GetMapping("/{expenseId}")
    public ExpenseResponse getExpense(@PathVariable Integer expenseId) {
        return expenseService.getExpense(expenseId);
    }

    @GetMapping("/all")
    public List<ExpenseResponse> getAllExpense() {
        return expenseService.getAllExpense();
    }

    @PutMapping("/update/user/{userId}/expense/{expenseId}")
    public String updateExpense(
            @PathVariable Integer userId,
            @PathVariable Integer expenseId,
            @RequestBody ExpenseRequest expenseRequest) {

        return expenseService.updateExpense(userId, expenseId, expenseRequest);

    }

    @DeleteMapping("/delete/user/{userId}/expense/{expenseId}")
    public String deleteExpense(@PathVariable Integer userId,
                                @PathVariable Integer expenseId) {
        return expenseService.deleteExpense(userId, expenseId);
    }

}
