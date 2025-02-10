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

    @GetMapping("/user/{userId}")
    public List<Income> getIncomeForUser(@PathVariable Integer userId) {
        return incomeService.getIncomeForUser(userId);
    }

    @GetMapping("/{incomeId}")
    public IncomeResponse getIncome(@PathVariable Integer incomeId) {
        return incomeService.getIncome(incomeId);
    }

    @GetMapping("/all")
    public List<IncomeResponse> getAllIncome() {
        return incomeService.getAllIncome();
    }

    @PutMapping("/update/user/{userId}/income/{incomeId}")
    public String updateIncome(
            @PathVariable Integer userId,
            @PathVariable Integer incomeId,
            @RequestBody IncomeRequest incomeRequest) {

        return incomeService.updateIncome(userId, incomeId, incomeRequest);

    }

    @DeleteMapping("/delete/user/{userId}/income/{incomeId}")
    public String deleteIncome(@PathVariable Integer userId,
                               @PathVariable Integer incomeId) {
        return incomeService.deleteIncome(userId, incomeId);
    }

}

