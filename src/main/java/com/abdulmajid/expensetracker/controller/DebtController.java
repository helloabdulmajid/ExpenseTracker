package com.abdulmajid.expensetracker.controller;

import com.abdulmajid.expensetracker.dto.request.DebtRequest;
import com.abdulmajid.expensetracker.dto.response.DebtResponse;
import com.abdulmajid.expensetracker.service.DebtService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users/{userId}/debts")

@RequiredArgsConstructor
@Tag(
        name = "Debt APIs",
        description = "Manage user debts"
)
public class DebtController {

    private final DebtService debtService;

    // CREATE DEBT
    @PostMapping
    public ResponseEntity<DebtResponse> createDebtForUser(

            @PathVariable Integer userId,

            @Valid @RequestBody DebtRequest debtRequest
    ) {

        DebtResponse response =
                debtService.createDebtForUser(userId, debtRequest);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    // GET ALL USER DEBTS
    @GetMapping
    public ResponseEntity<List<DebtResponse>> getAllDebtForUser(

            @PathVariable Integer userId
    ) {

        return ResponseEntity.ok(
                debtService.getAllDebtForUser(userId)
        );
    }

    // GET SINGLE DEBT
    @GetMapping("/{debtId}")
    public ResponseEntity<DebtResponse> getDebt(

            @PathVariable Integer userId,

            @PathVariable Integer debtId
    ) {

        return ResponseEntity.ok(
                debtService.getDebt(userId, debtId)
        );
    }

    // UPDATE DEBT
    @PutMapping("/{debtId}")
    public ResponseEntity<DebtResponse> updateDebt(

            @PathVariable Integer userId,

            @PathVariable Integer debtId,

            @Valid @RequestBody DebtRequest debtRequest
    ) {

        return ResponseEntity.ok(
                debtService.updateDebt(
                        userId,
                        debtId,
                        debtRequest
                )
        );
    }

    // DELETE DEBT
    @DeleteMapping("/{debtId}")
    public ResponseEntity<Void> deleteDebt(

            @PathVariable Integer userId,

            @PathVariable Integer debtId
    ) {

        debtService.deleteDebt(userId, debtId);

        return ResponseEntity.noContent().build();
    }
}