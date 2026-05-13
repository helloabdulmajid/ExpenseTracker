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
@RequestMapping("/loans")

@RequiredArgsConstructor

public class AuthenticatedLoanController {

    private final LoanService loanService;

    // CREATE LOAN
    @PostMapping
    public ResponseEntity<LoanResponse>
    createLoan(

            @Valid
            @RequestBody
            LoanRequest loanRequest
    ) {

        return ResponseEntity
                .status(HttpStatus.CREATED)

                .body(

                        loanService
                                .createLoan(
                                        loanRequest
                                )
                );
    }

    // GET ALL CURRENT USER LOANS
    @GetMapping("/me")
    public ResponseEntity<List<LoanResponse>>
    getCurrentUserLoans() {

        return ResponseEntity.ok(

                loanService
                        .getCurrentUserLoans()
        );
    }

    // GET SINGLE CURRENT USER LOAN
    @GetMapping("/me/{loanId}")
    public ResponseEntity<LoanResponse>
    getCurrentUserLoan(

            @PathVariable Integer loanId
    ) {

        return ResponseEntity.ok(

                loanService
                        .getCurrentUserLoan(
                                loanId
                        )
        );
    }

    // UPDATE LOAN
    @PutMapping("/{loanId}")
    public ResponseEntity<LoanResponse>
    updateLoan(

            @PathVariable Integer loanId,

            @Valid
            @RequestBody
            LoanRequest loanRequest
    ) {

        return ResponseEntity.ok(

                loanService
                        .updateLoan(
                                loanId,
                                loanRequest
                        )
        );
    }

    // DELETE LOAN
    @DeleteMapping("/{loanId}")
    public ResponseEntity<LoanResponse>
    deleteLoan(

            @PathVariable Integer loanId
    ) {

        return ResponseEntity.ok(

                loanService
                        .deleteLoan(
                                loanId
                        )
        );
    }
}