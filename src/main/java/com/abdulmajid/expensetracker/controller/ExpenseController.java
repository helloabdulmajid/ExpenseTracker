package com.abdulmajid.expensetracker.controller;

import com.abdulmajid.expensetracker.dto.ExpenseRequest;
import com.abdulmajid.expensetracker.dto.ExpenseResponse;
import com.abdulmajid.expensetracker.service.ExpenseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
}
