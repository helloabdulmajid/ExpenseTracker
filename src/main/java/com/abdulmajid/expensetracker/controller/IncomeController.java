package com.abdulmajid.expensetracker.controller;

import com.abdulmajid.expensetracker.dto.request.IncomeRequest;
import com.abdulmajid.expensetracker.dto.response.IncomeResponse;
import com.abdulmajid.expensetracker.service.IncomeService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users/{userId}/incomes")

@RequiredArgsConstructor
@Tag(
        name = "Income APIs",
        description = "Manage user incomes"
)
public class IncomeController {

    private final IncomeService incomeService;

    // CREATE INCOME
    @PostMapping
    public ResponseEntity<IncomeResponse> createIncomeForUser(

            @PathVariable Integer userId,

            @Valid @RequestBody IncomeRequest incomeRequest
    ) {

        IncomeResponse response =
                incomeService.createIncomeForUser(userId, incomeRequest);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    // GET ALL USER INCOMES
    @GetMapping
    public ResponseEntity<List<IncomeResponse>> getAllIncomeForUser(

            @PathVariable Integer userId
    ) {

        return ResponseEntity.ok(
                incomeService.getAllIncomeForUser(userId)
        );
    }

    // GET SINGLE INCOME
    @GetMapping("/{incomeId}")
    public ResponseEntity<IncomeResponse> getIncome(

            @PathVariable Integer userId,

            @PathVariable Integer incomeId
    ) {

        return ResponseEntity.ok(
                incomeService.getIncome(userId, incomeId)
        );
    }

    // UPDATE INCOME
    @PutMapping("/{incomeId}")
    public ResponseEntity<IncomeResponse> updateIncome(

            @PathVariable Integer userId,

            @PathVariable Integer incomeId,

            @Valid @RequestBody IncomeRequest incomeRequest
    ) {

        return ResponseEntity.ok(
                incomeService.updateIncome(
                        userId,
                        incomeId,
                        incomeRequest
                )
        );
    }

    // DELETE INCOME
    @DeleteMapping("/{incomeId}")
    public ResponseEntity<Void> deleteIncome(

            @PathVariable Integer userId,

            @PathVariable Integer incomeId
    ) {

        incomeService.deleteIncome(userId, incomeId);

        return ResponseEntity.noContent().build();
    }
}