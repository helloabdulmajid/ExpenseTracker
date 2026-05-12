package com.abdulmajid.expensetracker.service;

import com.abdulmajid.expensetracker.dto.request.ExpenseCategoryRequest;
import com.abdulmajid.expensetracker.dto.response.ExpenseCategoryResponse;

import java.util.List;

public interface ExpenseCategoryService {

    List<ExpenseCategoryResponse> getCategoriesForUser(
            Integer userId
    );

    ExpenseCategoryResponse createCategoryForUser(

            Integer userId,

            ExpenseCategoryRequest expenseCategoryRequest
    );

    List<ExpenseCategoryResponse> getAllCategories();

    List<ExpenseCategoryResponse> getDefaultCategories();
}