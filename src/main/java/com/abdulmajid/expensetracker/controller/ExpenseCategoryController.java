package com.abdulmajid.expensetracker.controller;

import com.abdulmajid.expensetracker.dto.ExpenseCategoryRequest;
import com.abdulmajid.expensetracker.dto.ExpenseCategoryResponse;
import com.abdulmajid.expensetracker.model.ExpenseCategory;
import com.abdulmajid.expensetracker.service.ExpenseCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("expense")
public class ExpenseCategoryController {

    @Autowired
    private ExpenseCategoryService expenseCategoryService;

    @PostMapping("/category/create/{userId}")
    public ResponseEntity<String> createCategoryForUser(@PathVariable Integer userId, @RequestBody ExpenseCategoryRequest expenseCategoryRequest) {
        expenseCategoryService.createCategoryForUser(userId, expenseCategoryRequest);
        return ResponseEntity.ok("Category added successfully!");
    }

    @GetMapping("/categories/{userId}")
    public List<ExpenseCategory> getCategoriesForUser(@PathVariable Integer userId) {
        return expenseCategoryService.getCategoriesForUser(userId);
    }

    @GetMapping("/categories")
    public List<ExpenseCategoryResponse> getAllCategories() {
        return expenseCategoryService.getAllCategories();
    }
}
