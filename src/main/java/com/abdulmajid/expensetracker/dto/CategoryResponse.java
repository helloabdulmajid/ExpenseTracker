package com.abdulmajid.expensetracker.dto;

public class CategoryResponse {
    private Integer id;
    private String categoryName;
    private boolean isDefaultCategory;

    public CategoryResponse() {
    }

    public CategoryResponse(Integer id, String categoryName, boolean isDefaultCategory) {
        this.id = id;
        this.categoryName = categoryName;
        this.isDefaultCategory = isDefaultCategory;
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

    public boolean isDefaultCategory() {
        return isDefaultCategory;
    }

    public void setDefaultCategory(boolean defaultCategory) {
        isDefaultCategory = defaultCategory;
    }
}
