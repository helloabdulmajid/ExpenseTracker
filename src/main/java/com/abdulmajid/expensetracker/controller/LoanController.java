package com.abdulmajid.expensetracker.controller;

import com.abdulmajid.expensetracker.dto.request.LoanRequest;
import com.abdulmajid.expensetracker.dto.response.LoanResponse;
import com.abdulmajid.expensetracker.service.LoanService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users/{userId}/loans")

@RequiredArgsConstructor

public class LoanController {

    private final LoanService loanService;

    // CREATE LOAN
    @PostMapping
    public ResponseEntity<LoanResponse> createLoanForUser(

            @PathVariable Integer userId,

            @Valid @RequestBody LoanRequest loanRequest
    ) {

        LoanResponse response =
                loanService.createLoanForUser(userId, loanRequest);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    // GET ALL USER LOANS
    @GetMapping
    public ResponseEntity<List<LoanResponse>> getAllLoanForUser(

            @PathVariable Integer userId
    ) {

        return ResponseEntity.ok(
                loanService.getAllLoanForUser(userId)
        );
    }

    // GET SINGLE LOAN
    @GetMapping("/{loanId}")
    public ResponseEntity<LoanResponse> getOneLoan(

            @PathVariable Integer userId,

            @PathVariable Integer loanId
    ) {

        return ResponseEntity.ok(
                loanService.getOneLoan(userId, loanId)
        );
    }

    // UPDATE LOAN
    @PutMapping("/{loanId}")
    public ResponseEntity<LoanResponse> updateLoan(

            @PathVariable Integer userId,

            @PathVariable Integer loanId,

            @Valid @RequestBody LoanRequest loanRequest
    ) {

        return ResponseEntity.ok(
                loanService.updateLoan(
                        userId,
                        loanId,
                        loanRequest
                )
        );
    }

    // DELETE LOAN
    @DeleteMapping("/{loanId}")
    public ResponseEntity<Void> deleteLoan(

            @PathVariable Integer userId,

            @PathVariable Integer loanId
    ) {

        loanService.deleteLoan(userId, loanId);

        return ResponseEntity.noContent().build();
    }
}