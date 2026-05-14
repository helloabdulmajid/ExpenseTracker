package com.abdulmajid.expensetracker.service;

import com.abdulmajid.expensetracker.dto.request.ExpenseCategoryRequest;
import com.abdulmajid.expensetracker.dto.response.ExpenseCategoryResponse;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface ExpenseCategoryService {

    List<ExpenseCategoryResponse>
    getMyCategories(Authentication authentication);

    ExpenseCategoryResponse
    createMyCategory(
            Authentication authentication,
            ExpenseCategoryRequest request
    );

    ExpenseCategoryResponse
    updateMyCategory(
            Authentication authentication,
            Integer categoryId,
            ExpenseCategoryRequest request
    );

    void deleteMyCategory(
            Authentication authentication,
            Integer categoryId
    );

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