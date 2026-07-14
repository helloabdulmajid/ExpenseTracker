package com.abdulmajid.expensetracker.service.impl;

import com.abdulmajid.expensetracker.dto.request.IncomeCategoryRequest;
import com.abdulmajid.expensetracker.dto.response.IncomeCategoryResponse;
import com.abdulmajid.expensetracker.exception.custom.CategoryNotFoundException;
import com.abdulmajid.expensetracker.exception.custom.UserNotFoundException;
import com.abdulmajid.expensetracker.model.IncomeCategory;
import com.abdulmajid.expensetracker.model.User;
import com.abdulmajid.expensetracker.repository.IncomeCategoryRepository;
import com.abdulmajid.expensetracker.repository.UserRepository;
import com.abdulmajid.expensetracker.service.IncomeCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor

public class IncomeCategoryServiceImpl
        implements IncomeCategoryService {

    private final IncomeCategoryRepository incomeCategoryRepository;

    private final UserRepository userRepository;

    // GET USER CATEGORIES (includes defaults)
    @Override
    public List<IncomeCategoryResponse>
    getIncomeCategoriesForUser(
            Integer userId
    ) {

        getUserById(userId);

        List<IncomeCategory> userCategories =
                incomeCategoryRepository
                        .findByUserId(userId);

        List<IncomeCategory> defaultCategories =
                incomeCategoryRepository
                        .findByDefaultCategoryTrue();

        return Stream.concat(
                        userCategories.stream(),
                        defaultCategories.stream()
                )
                .map(this::mapToResponse)
                .toList();
    }

    // CREATE CATEGORY
    @Override
    public IncomeCategoryResponse
    createIncomeCategoryForUser(

            Integer userId,

            IncomeCategoryRequest incomeCategoryRequest
    ) {

        User user = getUserById(userId);

        validateCategory(
                incomeCategoryRequest.getCategoryName(),
                user
        );

        IncomeCategory incomeCategory =
                new IncomeCategory();

        incomeCategory.setCategoryName(
                incomeCategoryRequest
                        .getCategoryName()
                        .trim()
                        .toUpperCase()
        );

        incomeCategory.setDefaultCategory(false);

        incomeCategory.setUser(user);

        IncomeCategory savedCategory =
                incomeCategoryRepository.save(
                        incomeCategory
                );

        return mapToResponse(savedCategory);
    }

    // GET ALL CATEGORIES
    @Override
    public List<IncomeCategoryResponse>
    getAllCategories() {

        return incomeCategoryRepository
                .findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    // GET DEFAULT CATEGORIES
    @Override
    public List<IncomeCategoryResponse>
    getDefaultCategories() {

        return incomeCategoryRepository
                .findByDefaultCategoryTrue()
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
                incomeCategoryRepository
                        .existsByCategoryNameAndDefaultCategory(
                                normalizedCategory,
                                true
                        );

        boolean userCategoryExists =
                incomeCategoryRepository
                        .existsByCategoryNameAndUser(
                                normalizedCategory,
                                user
                        );

        if (defaultCategoryExists
                || userCategoryExists) {

            throw new CategoryNotFoundException(
                    "Income category already exists"
            );
        }
    }

    // MAPPER METHOD
    private IncomeCategoryResponse mapToResponse(
            IncomeCategory incomeCategory
    ) {

        IncomeCategoryResponse response =
                new IncomeCategoryResponse();

        response.setId(
                incomeCategory.getId()
        );

        response.setCategoryName(
                incomeCategory.getCategoryName()
        );

        response.setDefaultCategory(
                incomeCategory.isDefaultCategory()
        );

        if (incomeCategory.getUser() != null) {

            response.setUserId(
                    incomeCategory.getUser().getId()
            );
        }

        return response;
    }
}