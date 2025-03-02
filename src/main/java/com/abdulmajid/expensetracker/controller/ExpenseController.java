package com.abdulmajid.expensetracker.controller;

import com.abdulmajid.expensetracker.dto.ExpenseRequest;
import com.abdulmajid.expensetracker.dto.ExpenseResponse;
import com.abdulmajid.expensetracker.service.ExpenseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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

    @GetMapping("/monthly/{userId}/{month}")
    public List<ExpenseResponse> getMonthlyExpenses(@PathVariable Integer userId, @PathVariable Integer month) {
        return expenseService.getMonthlyExpenses(userId, month);
    }

    @GetMapping("/weekly/{userId}/{week}")
    public List<ExpenseResponse> getWeeklyExpenses(@PathVariable Integer userId, @PathVariable Integer week) {
        return expenseService.getWeeklyExpenses(userId, week);
    }

//    @GetMapping("/daily/{userId}/{day}")
//    public List<ExpenseResponse> getDailyExpenses(@PathVariable Integer userId, @PathVariable Integer day) {
//        return expenseService.getDailyExpenses(userId, day);
//    }

    @GetMapping("/daily")
    public List<ExpenseResponse> getDailyExpenses(
            @RequestParam Integer userId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate day) {
        return expenseService.getDailyExpenses(userId, day);
    }

}
