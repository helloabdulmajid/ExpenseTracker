package com.abdulmajid.expensetracker.service;

import com.abdulmajid.expensetracker.dto.request.IncomeCategoryRequest;
import com.abdulmajid.expensetracker.dto.response.IncomeCategoryResponse;

import java.util.List;

public interface IncomeCategoryService {

    List<IncomeCategoryResponse> getIncomeCategoriesForUser(
            Integer userId
    );

    IncomeCategoryResponse createIncomeCategoryForUser(

            Integer userId,

            IncomeCategoryRequest incomeCategoryRequest
    );

    List<IncomeCategoryResponse> getAllCategories();

    List<IncomeCategoryResponse> getDefaultCategories();
}