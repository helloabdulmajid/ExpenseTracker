package com.abdulmajid.expensetracker.dto;

import jakarta.validation.constraints.NotBlank;

public class ExpenseCategoryRequest {
    private Integer id;

    @NotBlank(message = "Category Name name is required")
    private String categoryName; // Only required field in request

    // Constructors
    public ExpenseCategoryRequest() {
    }

    public ExpenseCategoryRequest(Integer id, String categoryName) {
        this.id = id;
        this.categoryName = categoryName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
