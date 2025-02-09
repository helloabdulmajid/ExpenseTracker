package com.abdulmajid.expensetracker.controller;

import com.abdulmajid.expensetracker.dto.LoanRequest;
import com.abdulmajid.expensetracker.dto.LoanResponse;
import com.abdulmajid.expensetracker.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("loan")
public class LoanController {

    @Autowired
    private LoanService loanService;

    @PostMapping("/create/{userId}")
    public LoanResponse createLoanForUser(@PathVariable Integer userId,
                                          @RequestBody LoanRequest loanRequest) {
        return loanService.createLoanForUser(userId, loanRequest);
    }
//    @GetMapping("/{userId}")
//    public Debt getDebtForUser(@PathVariable Integer userId) {
//        return debtService.getDebtForUser(userId);
//    }

    @GetMapping("/{userId}")
    public List<LoanResponse> getAllLoanForUser(@PathVariable Integer userId) {
        return loanService.getAllLoanForUser(userId);
    }
}
