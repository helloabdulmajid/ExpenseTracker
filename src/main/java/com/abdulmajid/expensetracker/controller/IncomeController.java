package com.abdulmajid.expensetracker.controller;

import com.abdulmajid.expensetracker.dto.IncomeRequest;
import com.abdulmajid.expensetracker.dto.IncomeResponse;
import com.abdulmajid.expensetracker.model.Income;
import com.abdulmajid.expensetracker.service.IncomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("income")
public class IncomeController {
    @Autowired
    private IncomeService incomeService;

    @PostMapping("/create/{userId}")
    public IncomeResponse createIncomeForUser(@PathVariable Integer userId,
                                              @RequestBody IncomeRequest incomeRequest) {
        return incomeService.createIncomeForUser(userId, incomeRequest);
    }

    @GetMapping("/{userId}")
    public List<Income> getIncomeForUser(@PathVariable Integer userId) {
        return incomeService.getIncomeForUser(userId);
    }

}

