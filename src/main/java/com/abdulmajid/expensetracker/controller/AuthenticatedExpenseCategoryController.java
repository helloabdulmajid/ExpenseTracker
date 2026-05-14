package com.abdulmajid.expensetracker.controller;

import com.abdulmajid.expensetracker.dto.request.ExpenseCategoryRequest;
import com.abdulmajid.expensetracker.dto.response.ExpenseCategoryResponse;
import com.abdulmajid.expensetracker.service.ExpenseCategoryService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("expense-categories")
@RequiredArgsConstructor
@Tag(
        name = "Authenticated Expense Category APIs",
        description = "Authenticated user expense category APIs"
)
public class AuthenticatedExpenseCategoryController {

    private final ExpenseCategoryService expenseCategoryService;

    // GET MY CATEGORIES
    @GetMapping("/me")
    public ResponseEntity<List<ExpenseCategoryResponse>>
    getMyCategories(Authentication authentication) {

        return ResponseEntity.ok(
                expenseCategoryService
                        .getMyCategories(authentication)
        );
    }

    // CREATE CATEGORY
    @PostMapping("/me")
    public ResponseEntity<ExpenseCategoryResponse>
    createMyCategory(

            Authentication authentication,

            @Valid
            @RequestBody
            ExpenseCategoryRequest request
    ) {

        ExpenseCategoryResponse response =
                expenseCategoryService
                        .createMyCategory(
                                authentication,
                                request
                        );

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    // UPDATE CATEGORY
    @PutMapping("/me/{categoryId}")
    public ResponseEntity<ExpenseCategoryResponse>
    updateMyCategory(

            Authentication authentication,

            @PathVariable
            Integer categoryId,

            @Valid
            @RequestBody
            ExpenseCategoryRequest request
    ) {

        return ResponseEntity.ok(
                expenseCategoryService
                        .updateMyCategory(
                                authentication,
                                categoryId,
                                request
                        )
        );
    }

    // DELETE CATEGORY
    @DeleteMapping("/me/{categoryId}")
    public ResponseEntity<String>
    deleteMyCategory(

            Authentication authentication,

            @PathVariable
            Integer categoryId
    ) {

        expenseCategoryService
                .deleteMyCategory(
                        authentication,
                        categoryId
                );

        return ResponseEntity.ok(
                "Category deleted successfully"
        );
    }
}