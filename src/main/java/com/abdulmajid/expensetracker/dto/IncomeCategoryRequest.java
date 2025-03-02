package com.abdulmajid.expensetracker.dto;

import jakarta.validation.constraints.NotBlank;

public class IncomeCategoryRequest {
    private Integer id;

    @NotBlank(message = "Category Name  is required")
    private String categoryName; // Only required field in request

    public IncomeCategoryRequest() {
    }

    public IncomeCategoryRequest(Integer id, String categoryName) {
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
