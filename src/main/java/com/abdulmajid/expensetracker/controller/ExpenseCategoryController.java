package com.abdulmajid.expensetracker.controller;

import com.abdulmajid.expensetracker.dto.request.ExpenseCategoryRequest;
import com.abdulmajid.expensetracker.dto.response.ExpenseCategoryResponse;
import com.abdulmajid.expensetracker.service.ExpenseCategoryService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("expense-categories")
@RequiredArgsConstructor
@Tag(
        name = "Expense Category APIs",
        description = "Manage expense categories"
)
public class ExpenseCategoryController {

    private final ExpenseCategoryService expenseCategoryService;

    // DEFAULT CATEGORY
    @GetMapping("/default")
    public ResponseEntity<List<ExpenseCategoryResponse>>
    getDefaultCategories() {

        return ResponseEntity.ok(
                expenseCategoryService.getDefaultCategories()
        );
    }

    // CREATE CATEGORY
    @PostMapping("/users/{userId}")
    public ResponseEntity<ExpenseCategoryResponse> createCategoryForUser(

            @PathVariable Integer userId,

            @Valid @RequestBody
            ExpenseCategoryRequest expenseCategoryRequest
    ) {

        ExpenseCategoryResponse response =
                expenseCategoryService.createCategoryForUser(
                        userId,
                        expenseCategoryRequest
                );

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    // GET USER CATEGORIES
    @GetMapping("/users/{userId}")
    public ResponseEntity<List<ExpenseCategoryResponse>> getCategoriesForUser(

            @PathVariable Integer userId
    ) {

        return ResponseEntity.ok(
                expenseCategoryService.getCategoriesForUser(userId)
        );
    }

    // GET ALL CATEGORIES
    @GetMapping("/users/{userId}/all")
    public ResponseEntity<List<ExpenseCategoryResponse>> getAllCategories() {

        return ResponseEntity.ok(
                expenseCategoryService.getAllCategories()
        );
    }
}