package com.abdulmajid.expensetracker.controller;

import com.abdulmajid.expensetracker.dto.request.IncomeCategoryRequest;
import com.abdulmajid.expensetracker.dto.response.IncomeCategoryResponse;
import com.abdulmajid.expensetracker.exception.custom.UserNotFoundException;
import com.abdulmajid.expensetracker.model.User;
import com.abdulmajid.expensetracker.repository.UserRepository;
import com.abdulmajid.expensetracker.security.utils.SecurityUtils;
import com.abdulmajid.expensetracker.service.IncomeCategoryService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("income-categories")
@RequiredArgsConstructor
@Tag(
        name = "Income Category APIs",
        description = "Manage income categories"
)
public class AuthenticatedIncomeCategoryController {

    private final IncomeCategoryService incomeCategoryService;

    private final UserRepository userRepository;

    // GET MY CATEGORIES
    @GetMapping("/me")
    public ResponseEntity<List<IncomeCategoryResponse>>
    getMyCategories() {

        Integer userId = getCurrentUserId();

        return ResponseEntity.ok(
                incomeCategoryService
                        .getIncomeCategoriesForUser(userId)
        );
    }

    // CREATE CATEGORY
    @PostMapping("/me")
    public ResponseEntity<IncomeCategoryResponse>
    createMyCategory(

            @Valid
            @RequestBody
            IncomeCategoryRequest request
    ) {

        Integer userId = getCurrentUserId();

        IncomeCategoryResponse response =
                incomeCategoryService
                        .createIncomeCategoryForUser(
                                userId,
                                request
                        );

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    private Integer getCurrentUserId() {

        String email = SecurityUtils.getCurrentUserEmail();

        User user = userRepository
                .findByEmail(email)
                .orElseThrow(() ->
                        new UserNotFoundException(
                                "User not found"
                        )
                );

        return user.getId();
    }
}
