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

    @GetMapping("/getloan/{loanId}")
    public LoanResponse getOneloan(@PathVariable Integer loanId) {
        return loanService.getOneLoan(loanId);
    }

    @GetMapping("/getloans/user/{userId}")
    public List<LoanResponse> getAllLoanForUser(@PathVariable Integer userId) {
        return loanService.getAllLoanForUser(userId);
    }

    @GetMapping("/all")
    public List<LoanResponse> getAllLoans() {
        return loanService.getAllLoans();
    }

    @PutMapping("/update/user/{userId}/loan/{loanId}")
    public String updateLoan(
            @PathVariable Integer userId,
            @PathVariable Integer loanId,
            @RequestBody LoanRequest loanRequest) {

        return loanService.updateLoan(userId, loanId, loanRequest);

    }

    @DeleteMapping("/delete/user/{userId}/loan/{loanId}")
    public String deleteLoan(@PathVariable Integer userId,
                             @PathVariable Integer loanId) {
        return loanService.deleteLoan(userId, loanId);
    }
}
