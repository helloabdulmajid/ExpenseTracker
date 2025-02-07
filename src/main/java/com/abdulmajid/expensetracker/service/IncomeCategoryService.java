package com.abdulmajid.expensetracker.service;

import com.abdulmajid.expensetracker.dto.IncomeCategoryRequest;
import com.abdulmajid.expensetracker.dto.IncomeCategoryResponse;
import com.abdulmajid.expensetracker.model.IncomeCategory;

import java.util.List;

public interface IncomeCategoryService {
    public List<IncomeCategory> getIncomeCategoriesForUser(Integer userId);

    public void createIncomeCategoryForUser(Integer userId, IncomeCategoryRequest incomeCategoryRequest);

    public List<IncomeCategoryResponse> getAllCategories();
}
