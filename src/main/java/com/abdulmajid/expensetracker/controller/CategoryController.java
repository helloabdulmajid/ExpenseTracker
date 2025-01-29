package com.abdulmajid.expensetracker.controller;

import com.abdulmajid.expensetracker.dto.CategoryRequest;
import com.abdulmajid.expensetracker.model.Category;
import com.abdulmajid.expensetracker.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("expense")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/category/create/{userId}")
    public ResponseEntity<String> createCategoryForUser(@PathVariable Integer userId, @RequestBody CategoryRequest categoryRequest) {
        categoryService.createCategoryForUser(userId, categoryRequest);
        return ResponseEntity.ok("Category added successfully!");
    }

    @GetMapping("/categories/{userId}")
    public List<Category> getCategoriesForUser(@PathVariable Integer userId) {
        return categoryService.getCategoriesForUser(userId);
    }

    @GetMapping("/categories")
    public List<Category> getAllCategories() {
        return categoryService.getAllCategories();
    }
}
