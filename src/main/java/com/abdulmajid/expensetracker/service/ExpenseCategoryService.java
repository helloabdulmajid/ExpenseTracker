package com.abdulmajid.expensetracker.service;

import com.abdulmajid.expensetracker.dto.ExpenseCategoryRequest;
import com.abdulmajid.expensetracker.dto.ExpenseCategoryResponse;
import com.abdulmajid.expensetracker.model.ExpenseCategory;

import java.util.List;

public interface ExpenseCategoryService {

    public List<ExpenseCategory> getCategoriesForUser(Integer userId);

    public void createCategoryForUser(Integer userId, ExpenseCategoryRequest expenseCategoryRequest);

    public List<ExpenseCategoryResponse> getAllCategories();
}
