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
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor

public class ExpenseCategoryServiceImpl
        implements ExpenseCategoryService {

    private final UserRepository userRepository;

    private final ExpenseCategoryRepository expenseCategoryRepository;

    // =========================================
    // AUTHENTICATED CATEGORY APIs
    // =========================================

    @Override
    public List<ExpenseCategoryResponse>
    getMyCategories(Authentication authentication) {

        User user = getAuthenticatedUser(authentication);

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

            Authentication authentication,

            ExpenseCategoryRequest request
    ) {

        User user = getAuthenticatedUser(authentication);

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

            Authentication authentication,

            Integer categoryId,

            ExpenseCategoryRequest request
    ) {

        User user = getAuthenticatedUser(authentication);

        ExpenseCategory category =
                expenseCategoryRepository
                        .findById(categoryId)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Category not found"
                                )
                        );

        if (category.isDefaultCategory()) {

            throw new RuntimeException(
                    "Default categories cannot be updated"
            );
        }

        if (
                category.getUser() == null ||

                        !category.getUser()
                                .getId()
                                .equals(user.getId())
        ) {

            throw new RuntimeException(
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

            Authentication authentication,

            Integer categoryId
    ) {

        User user = getAuthenticatedUser(authentication);

        ExpenseCategory category =
                expenseCategoryRepository
                        .findById(categoryId)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Category not found"
                                )
                        );

        if (category.isDefaultCategory()) {

            throw new RuntimeException(
                    "Default categories cannot be deleted"
            );
        }

        if (
                category.getUser() == null ||

                        !category.getUser()
                                .getId()
                                .equals(user.getId())
        ) {

            throw new RuntimeException(
                    "Unauthorized category access"
            );
        }

        expenseCategoryRepository
                .delete(category);
    }

    // =========================================
    // OLD APIs
    // =========================================

    @Override
    public List<ExpenseCategoryResponse>
    getCategoriesForUser(
            Integer userId
    ) {

        getUserById(userId);

        return expenseCategoryRepository
                .findByUserId(userId)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public ExpenseCategoryResponse
    createCategoryForUser(

            Integer userId,

            ExpenseCategoryRequest expenseCategoryRequest
    ) {

        User user = getUserById(userId);

        validateCategory(
                expenseCategoryRequest
                        .getCategoryName(),
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
                expenseCategoryRepository
                        .save(expenseCategory);

        return mapToResponse(savedCategory);
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

    private User getAuthenticatedUser(
            Authentication authentication
    ) {

        String email = authentication.getName();

        return userRepository
                .findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException(
                                "User not found"
                        )
                );
    }

    private User getUserById(
            Integer userId
    ) {

        return userRepository
                .findById(userId)
                .orElseThrow(() ->
                        new UserNotFoundException(
                                "User not found with ID: "
                                        + userId
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