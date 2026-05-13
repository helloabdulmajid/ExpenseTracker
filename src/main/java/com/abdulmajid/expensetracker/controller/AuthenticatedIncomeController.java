package com.abdulmajid.expensetracker.controller;

import com.abdulmajid.expensetracker.dto.request.IncomeRequest;
import com.abdulmajid.expensetracker.dto.response.IncomeResponse;
import com.abdulmajid.expensetracker.service.IncomeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/incomes")

@RequiredArgsConstructor

public class AuthenticatedIncomeController {

    private final IncomeService incomeService;

    // CREATE INCOME
    @PostMapping
    public ResponseEntity<IncomeResponse>
    createIncome(

            @Valid
            @RequestBody
            IncomeRequest incomeRequest
    ) {

        return ResponseEntity
                .status(HttpStatus.CREATED)

                .body(

                        incomeService
                                .createIncome(
                                        incomeRequest
                                )
                );
    }

    // GET ALL CURRENT USER INCOMES
    @GetMapping("/me")
    public ResponseEntity<List<IncomeResponse>>
    getCurrentUserIncomes() {

        return ResponseEntity.ok(

                incomeService
                        .getCurrentUserIncomes()
        );
    }

    // GET SINGLE CURRENT USER INCOME
    @GetMapping("/me/{incomeId}")
    public ResponseEntity<IncomeResponse>
    getCurrentUserIncome(

            @PathVariable Integer incomeId
    ) {

        return ResponseEntity.ok(

                incomeService
                        .getCurrentUserIncome(
                                incomeId
                        )
        );
    }

    // UPDATE INCOME
    @PutMapping("/{incomeId}")
    public ResponseEntity<IncomeResponse>
    updateIncome(

            @PathVariable Integer incomeId,

            @Valid
            @RequestBody
            IncomeRequest incomeRequest
    ) {

        return ResponseEntity.ok(

                incomeService
                        .updateIncome(
                                incomeId,
                                incomeRequest
                        )
        );
    }

    // DELETE INCOME
    @DeleteMapping("/{incomeId}")
    public ResponseEntity<IncomeResponse>
    deleteIncome(

            @PathVariable Integer incomeId
    ) {

        return ResponseEntity.ok(

                incomeService
                        .deleteIncome(
                                incomeId
                        )
        );
    }
}