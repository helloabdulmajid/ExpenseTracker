package com.abdulmajid.expensetracker.controller;

import com.abdulmajid.expensetracker.dto.request.IncomeCategoryRequest;
import com.abdulmajid.expensetracker.dto.response.IncomeCategoryResponse;
import com.abdulmajid.expensetracker.service.IncomeCategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("income-categories")

@RequiredArgsConstructor

public class IncomeCategoryController {

    private final IncomeCategoryService incomeCategoryService;

    // CREATE CATEGORY
    @PostMapping("/users/{userId}")
    public ResponseEntity<IncomeCategoryResponse> createCategoryForUser(

            @PathVariable Integer userId,

            @Valid @RequestBody
            IncomeCategoryRequest incomeCategoryRequest
    ) {

        IncomeCategoryResponse response =
                incomeCategoryService.createIncomeCategoryForUser(
                        userId,
                        incomeCategoryRequest
                );

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    // GET USER CATEGORIES
    @GetMapping("/users/{userId}")
    public ResponseEntity<List<IncomeCategoryResponse>> getCategoriesForUser(

            @PathVariable Integer userId
    ) {

        return ResponseEntity.ok(
                incomeCategoryService.getIncomeCategoriesForUser(userId)
        );
    }

    // GET ALL CATEGORIES
    @GetMapping("/users/{userId}/all")
    public ResponseEntity<List<IncomeCategoryResponse>> getAllCategories() {

        return ResponseEntity.ok(
                incomeCategoryService.getAllCategories()
        );
    }

    @GetMapping("/default")
    public ResponseEntity<List<IncomeCategoryResponse>> getDefaultCategories() {

        return ResponseEntity.ok(
                incomeCategoryService.getDefaultCategories()
        );
    }
}