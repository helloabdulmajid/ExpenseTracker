package com.abdulmajid.expensetracker.service;

import com.abdulmajid.expensetracker.dto.request.ExpenseCategoryRequest;
import com.abdulmajid.expensetracker.dto.response.ExpenseCategoryResponse;

import java.util.List;

public interface ExpenseCategoryService {

    List<ExpenseCategoryResponse>
    getMyCategories();

    ExpenseCategoryResponse
    createMyCategory(
            ExpenseCategoryRequest request
    );

    ExpenseCategoryResponse
    updateMyCategory(
            Integer categoryId,
            ExpenseCategoryRequest request
    );

    void deleteMyCategory(
            Integer categoryId
    );

    List<ExpenseCategoryResponse> getAllCategories();

    List<ExpenseCategoryResponse> getDefaultCategories();
}