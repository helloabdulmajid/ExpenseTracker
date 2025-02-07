package com.abdulmajid.expensetracker.service.impl;

import com.abdulmajid.expensetracker.dto.ExpenseCategoryRequest;
import com.abdulmajid.expensetracker.dto.ExpenseCategoryResponse;
import com.abdulmajid.expensetracker.exception.custom.CategoryNotFoundException;
import com.abdulmajid.expensetracker.exception.custom.UserNotFoundException;
import com.abdulmajid.expensetracker.model.ExpenseCategory;
import com.abdulmajid.expensetracker.model.User;
import com.abdulmajid.expensetracker.repository.ExpenseCategoryRepository;
import com.abdulmajid.expensetracker.repository.UserRepository;
import com.abdulmajid.expensetracker.service.ExpenseCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ExpenseExpenseCategoryServiceImpl implements ExpenseCategoryService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ExpenseCategoryRepository expenseCategoryRepository;


    @Override
    public List<ExpenseCategory> getCategoriesForUser(Integer userId) {
        User existsUser = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + userId));

        return expenseCategoryRepository.findByUserId(userId);
    }

    @Override
    public void createCategoryForUser(Integer userId, ExpenseCategoryRequest expenseCategoryRequest) {
        // Check if user exists
        User existsUser = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + userId));

        // Check if the category already exists for the user
//        if (categoryRepository.existsByCategoryName(categoryRequest.getCategoryName()) ||
//                categoryRepository.existsByCategoryNameAndUser(categoryRequest.getCategoryName(), existsUser)) {
//            throw new CategoryNotFoundException("Category already exists for this user");
//        }

        if (expenseCategoryRepository.existsByCategoryNameAndIsDefaultCategory(expenseCategoryRequest.getCategoryName(), true) ||
                expenseCategoryRepository.existsByCategoryNameAndUser(expenseCategoryRequest.getCategoryName(), existsUser)) {
            throw new CategoryNotFoundException("Category already exists for this user");
        }

        // Create new category for the user

        ExpenseCategory expenseCategory = new ExpenseCategory();
        expenseCategory.setCategoryName(expenseCategoryRequest.getCategoryName().toUpperCase());
        expenseCategory.setDefaultCategory(false);
        expenseCategory.setUser(existsUser);

        expenseCategoryRepository.save(expenseCategory);
    }

    @Override
    public List<ExpenseCategoryResponse> getAllCategories() {
        List<ExpenseCategory> expenseCategoryList = expenseCategoryRepository.findAll();

        // Convert each ExpenseCategory entity to ExpenseCategoryResponse DTO

        ArrayList<ExpenseCategoryResponse> responseList = new ArrayList<>();

        for (ExpenseCategory expenseCategory : expenseCategoryList) {
            ExpenseCategoryResponse response = new ExpenseCategoryResponse(
                    expenseCategory.getId(), expenseCategory.getCategoryName(),
                    expenseCategory.isDefaultCategory()
            );
            responseList.add(response);

        }
        return responseList;
    }

}
