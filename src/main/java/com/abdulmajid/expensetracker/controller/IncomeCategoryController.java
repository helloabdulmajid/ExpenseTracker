package com.abdulmajid.expensetracker.controller;

import com.abdulmajid.expensetracker.dto.IncomeCategoryRequest;
import com.abdulmajid.expensetracker.dto.IncomeCategoryResponse;
import com.abdulmajid.expensetracker.model.IncomeCategory;
import com.abdulmajid.expensetracker.service.IncomeCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("income")
public class IncomeCategoryController {

    @Autowired
    private IncomeCategoryService incomeCategoryService;

    @PostMapping("/category/create/{userId}")
    public ResponseEntity<String> createCategoryForUser(@PathVariable Integer userId,
                                                        @RequestBody IncomeCategoryRequest incomeCategoryRequest) {
        incomeCategoryService.createIncomeCategoryForUser(userId, incomeCategoryRequest);
        return ResponseEntity.ok("Income Category added successfully!");
    }

    //fix this only cateroy return not user
    @GetMapping("/categories/{userId}")
    public List<IncomeCategory> getCategoriesForUser(@PathVariable Integer userId) {
        return incomeCategoryService.getIncomeCategoriesForUser(userId);
    }

    @GetMapping("/categories")
    public List<IncomeCategoryResponse> getAllCategories() {
        return incomeCategoryService.getAllCategories();
    }

    //add another one here  getAllIncomeCategoryWithAllUser
}
