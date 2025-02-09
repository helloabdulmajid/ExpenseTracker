package com.abdulmajid.expensetracker.controller;

import com.abdulmajid.expensetracker.dto.DebtRequest;
import com.abdulmajid.expensetracker.dto.DebtResponse;
import com.abdulmajid.expensetracker.service.DebtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("debt")
public class DebtController {

    @Autowired
    private DebtService debtService;

    @PostMapping("/create/{userId}")
    public DebtResponse createDebtForUser(@PathVariable Integer userId,
                                          @RequestBody DebtRequest debtRequest) {
        return debtService.createDebtForUser(userId, debtRequest);
    }
//    @GetMapping("/{userId}")
//    public Debt getDebtForUser(@PathVariable Integer userId) {
//        return debtService.getDebtForUser(userId);
//    }

    @GetMapping("/{userId}")
    public List<DebtResponse> getAllDebtForUser(@PathVariable Integer userId) {
        return debtService.getAllDebtForUser(userId);
    }
}
