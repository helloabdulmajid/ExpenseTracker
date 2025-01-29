package com.abdulmajid.expensetracker.service;

import com.abdulmajid.expensetracker.dto.CategoryRequest;
import com.abdulmajid.expensetracker.model.Category;

import java.util.List;

public interface CategoryService {

    public List<Category> getCategoriesForUser(Integer userId);

    public void createCategoryForUser(Integer userId, CategoryRequest categoryRequest);

    public List<Category> getAllCategories();
}
