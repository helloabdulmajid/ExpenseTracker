package com.abdulmajid.expensetracker.dto;

public class ExpenseCategoryRequest {
    private Integer id;
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
