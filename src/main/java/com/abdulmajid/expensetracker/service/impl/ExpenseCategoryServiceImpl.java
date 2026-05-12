package com.abdulmajid.expensetracker.service.impl;

import com.abdulmajid.expensetracker.dto.request.ExpenseCategoryRequest;
import com.abdulmajid.expensetracker.dto.response.ExpenseCategoryResponse;
import com.abdulmajid.expensetracker.exception.custom.CategoryNotFoundException;
import com.abdulmajid.expensetracker.exception.custom.UserNotFoundException;
import com.abdulmajid.expensetracker.model.ExpenseCategory;
import com.abdulmajid.expensetracker.model.User;
import com.abdulmajid.expensetracker.repository.ExpenseCategoryRepository;
import com.abdulmajid.expensetracker.repository.UserRepository;
import com.abdulmajid.expensetracker.service.ExpenseCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor

public class ExpenseCategoryServiceImpl
        implements ExpenseCategoryService {

    private final UserRepository userRepository;

    private final ExpenseCategoryRepository expenseCategoryRepository;

    // GET USER CATEGORIES
    @Override
    public List<ExpenseCategoryResponse> getCategoriesForUser(
            Integer userId
    ) {

        getUserById(userId);

        return expenseCategoryRepository.findByUserId(userId)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    // CREATE CATEGORY
    @Override
    public ExpenseCategoryResponse createCategoryForUser(

            Integer userId,

            ExpenseCategoryRequest expenseCategoryRequest
    ) {

        User user = getUserById(userId);

        validateCategory(
                expenseCategoryRequest.getCategoryName(),
                user
        );

        ExpenseCategory expenseCategory =
                new ExpenseCategory();

        expenseCategory.setCategoryName(
                expenseCategoryRequest
                        .getCategoryName()
                        .trim()
                        .toUpperCase()
        );

        expenseCategory.setDefaultCategory(false);

        expenseCategory.setUser(user);

        ExpenseCategory savedCategory =
                expenseCategoryRepository.save(expenseCategory);

        return mapToResponse(savedCategory);
    }

    // GET ALL CATEGORIES
    @Override
    public List<ExpenseCategoryResponse> getAllCategories() {

        return expenseCategoryRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    // HELPER METHOD
    private User getUserById(
            Integer userId
    ) {

        return userRepository.findById(userId)
                .orElseThrow(() ->
                        new UserNotFoundException(
                                "User not found with ID: "
                                        + userId
                        )
                );
    }

    // HELPER METHOD
    private void validateCategory(

            String categoryName,

            User user
    ) {

        String normalizedCategory =
                categoryName
                        .trim()
                        .toUpperCase();

        boolean defaultCategoryExists =
                expenseCategoryRepository
                        .existsByCategoryNameAndDefaultCategory(
                                normalizedCategory,
                                true
                        );

        boolean userCategoryExists =
                expenseCategoryRepository
                        .existsByCategoryNameAndUser(
                                normalizedCategory,
                                user
                        );

        if (defaultCategoryExists || userCategoryExists) {

            throw new CategoryNotFoundException(
                    "Expense category already exists"
            );
        }
    }

    // MAPPER METHOD
    private ExpenseCategoryResponse mapToResponse(
            ExpenseCategory expenseCategory
    ) {

        ExpenseCategoryResponse response =
                new ExpenseCategoryResponse();

        response.setId(
                expenseCategory.getId()
        );

        response.setCategoryName(
                expenseCategory.getCategoryName()
        );

        response.setDefaultCategory(
                expenseCategory.isDefaultCategory()
        );

        if (expenseCategory.getUser() != null) {

            response.setUserId(
                    expenseCategory.getUser().getId()
            );
        }

        return response;
    }

    @Override
    public List<ExpenseCategoryResponse>
    getDefaultCategories() {

        return expenseCategoryRepository
                .findByDefaultCategoryTrue()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }
}