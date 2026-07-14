package com.abdulmajid.expensetracker.service.impl;

import com.abdulmajid.expensetracker.dto.request.ExpenseCategoryRequest;
import com.abdulmajid.expensetracker.dto.response.ExpenseCategoryResponse;
import com.abdulmajid.expensetracker.exception.custom.CategoryNotFoundException;
import com.abdulmajid.expensetracker.exception.custom.InvalidArgumentException;
import com.abdulmajid.expensetracker.exception.custom.UserNotFoundException;
import com.abdulmajid.expensetracker.model.ExpenseCategory;
import com.abdulmajid.expensetracker.model.User;
import com.abdulmajid.expensetracker.repository.ExpenseCategoryRepository;
import com.abdulmajid.expensetracker.repository.UserRepository;
import com.abdulmajid.expensetracker.security.utils.SecurityUtils;
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

    @Override
    public List<ExpenseCategoryResponse>
    getMyCategories() {

        User user = getCurrentUser();

        List<ExpenseCategory> categories =
                expenseCategoryRepository
                        .findAllCategoriesForUser(
                                user.getId()
                        );

        return categories.stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public ExpenseCategoryResponse
    createMyCategory(
            ExpenseCategoryRequest request
    ) {

        User user = getCurrentUser();

        validateCategory(
                request.getCategoryName(),
                user
        );

        ExpenseCategory category =
                new ExpenseCategory();

        category.setCategoryName(
                request.getCategoryName()
                        .trim()
                        .toUpperCase()
        );

        category.setDefaultCategory(false);

        category.setUser(user);

        ExpenseCategory savedCategory =
                expenseCategoryRepository
                        .save(category);

        return mapToResponse(savedCategory);
    }

    @Override
    public ExpenseCategoryResponse
    updateMyCategory(
            Integer categoryId,
            ExpenseCategoryRequest request
    ) {

        User user = getCurrentUser();

        ExpenseCategory category =
                expenseCategoryRepository
                        .findById(categoryId)
                        .orElseThrow(() ->
                                new CategoryNotFoundException(
                                        "Category not found"
                                )
                        );

        if (category.isDefaultCategory()) {

            throw new InvalidArgumentException(
                    "Default categories cannot be updated"
            );
        }

        if (
                category.getUser() == null ||

                        !category.getUser()
                                .getId()
                                .equals(user.getId())
        ) {

            throw new InvalidArgumentException(
                    "Unauthorized category access"
            );
        }

        category.setCategoryName(
                request.getCategoryName()
                        .trim()
                        .toUpperCase()
        );

        ExpenseCategory updatedCategory =
                expenseCategoryRepository
                        .save(category);

        return mapToResponse(updatedCategory);
    }

    @Override
    public void deleteMyCategory(
            Integer categoryId
    ) {

        User user = getCurrentUser();

        ExpenseCategory category =
                expenseCategoryRepository
                        .findById(categoryId)
                        .orElseThrow(() ->
                                new CategoryNotFoundException(
                                        "Category not found"
                                )
                        );

        if (category.isDefaultCategory()) {

            throw new InvalidArgumentException(
                    "Default categories cannot be deleted"
            );
        }

        if (
                category.getUser() == null ||

                        !category.getUser()
                                .getId()
                                .equals(user.getId())
        ) {

            throw new InvalidArgumentException(
                    "Unauthorized category access"
            );
        }

        expenseCategoryRepository
                .delete(category);
    }

    @Override
    public List<ExpenseCategoryResponse>
    getAllCategories() {

        return expenseCategoryRepository
                .findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
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

    // =========================================
    // HELPER METHODS
    // =========================================

    private User getCurrentUser() {

        String email = SecurityUtils.getCurrentUserEmail();

        return userRepository
                .findByEmail(email)
                .orElseThrow(() ->
                        new UserNotFoundException(
                                "User not found"
                        )
                );
    }

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

        if (
                defaultCategoryExists ||

                        userCategoryExists
        ) {

            throw new CategoryNotFoundException(
                    "Expense category already exists"
            );
        }
    }

    private ExpenseCategoryResponse
    mapToResponse(
            ExpenseCategory expenseCategory
    ) {

        return new ExpenseCategoryResponse(

                expenseCategory.getId(),

                expenseCategory.getCategoryName(),

                expenseCategory.isDefaultCategory(),

                expenseCategory.getUser() != null
                        ? expenseCategory
                        .getUser()
                        .getId()
                        : null
        );
    }
}