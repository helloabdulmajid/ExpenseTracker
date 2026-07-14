package com.abdulmajid.expensetracker.controller;

import com.abdulmajid.expensetracker.dto.request.DebtRequest;
import com.abdulmajid.expensetracker.dto.response.DebtResponse;
import com.abdulmajid.expensetracker.service.DebtService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/debts")

@RequiredArgsConstructor

public class AuthenticatedDebtController {

    private final DebtService debtService;

    // CREATE DEBT
    @PostMapping
    public ResponseEntity<DebtResponse>
    createDebt(

            @Valid
            @RequestBody
            DebtRequest debtRequest
    ) {

        return ResponseEntity
                .status(HttpStatus.CREATED)

                .body(

                        debtService
                                .createDebt(
                                        debtRequest
                                )
                );
    }

    // GET ALL DEBTS
    @GetMapping("/me")
    public ResponseEntity<List<DebtResponse>>
    getCurrentUserDebts() {

        return ResponseEntity.ok(

                debtService
                        .getCurrentUserDebts()
        );
    }

    // GET SINGLE DEBT
    @GetMapping("/me/{debtId}")
    public ResponseEntity<DebtResponse>
    getCurrentUserDebt(

            @PathVariable Integer debtId
    ) {

        return ResponseEntity.ok(

                debtService
                        .getCurrentUserDebt(
                                debtId
                        )
        );
    }

    // UPDATE DEBT
    @PutMapping("/{debtId}")
    public ResponseEntity<DebtResponse>
    updateDebt(

            @PathVariable Integer debtId,

            @Valid
            @RequestBody
            DebtRequest debtRequest
    ) {

        return ResponseEntity.ok(

                debtService
                        .updateDebt(
                                debtId,
                                debtRequest
                        )
        );
    }

    // DELETE DEBT
    @DeleteMapping("/{debtId}")
    public ResponseEntity<DebtResponse>
    deleteDebt(

            @PathVariable Integer debtId
    ) {

        return ResponseEntity.ok(

                debtService
                        .deleteDebt(
                                debtId
                        )
        );
    }
}