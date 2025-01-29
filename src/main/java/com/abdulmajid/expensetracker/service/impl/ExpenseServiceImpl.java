package com.abdulmajid.expensetracker.service.impl;

import com.abdulmajid.expensetracker.dto.CategoryResponse;
import com.abdulmajid.expensetracker.dto.ExpenseRequest;
import com.abdulmajid.expensetracker.dto.ExpenseResponse;
import com.abdulmajid.expensetracker.exception.custom.CategoryNotFoundException;
import com.abdulmajid.expensetracker.exception.custom.UserNotFoundException;
import com.abdulmajid.expensetracker.model.Category;
import com.abdulmajid.expensetracker.model.Expense;
import com.abdulmajid.expensetracker.model.User;
import com.abdulmajid.expensetracker.repository.CategoryRepository;
import com.abdulmajid.expensetracker.repository.ExpenseRepository;
import com.abdulmajid.expensetracker.repository.UserRepository;
import com.abdulmajid.expensetracker.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExpenseServiceImpl implements ExpenseService {

    @Autowired
    private ExpenseRepository expenseRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public ExpenseResponse createExpense(Integer userId, ExpenseRequest expenseRequest) {

        // get user from userId(param)
        User existsUser = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + userId));

        //get category from categoryId
        Category dbCategory = categoryRepository.findById(expenseRequest.getCategoryId())
                .orElseThrow(() -> new CategoryNotFoundException("Category not found with ID: " + expenseRequest.getCategoryId()));

        //create expense
        Expense expense = new Expense(expenseRequest.getAmount(),
                expenseRequest.getPaymentMode(),
                expenseRequest.getNote(),
                expenseRequest.getDay(),
                expenseRequest.getDate(),
                expenseRequest.getCreatedAt(),
                expenseRequest.getUpdatedAt(),
                dbCategory,
                existsUser);


        // Save the expense in to db
        expenseRepository.save(expense);

        // Return ExpenseResponse (with category details)

        CategoryResponse categoryResponse = new CategoryResponse(dbCategory.getId(),
                dbCategory.getCategoryName(), dbCategory.isDefaultCategory());

        return new ExpenseResponse(expense.getId(), expense.getAmount(), expense.getPaymentMode(),
                expense.getNote(), expense.getDay(), expense.getDate(), expense.getCreatedAt(),
                expense.getUpdatedAt(), categoryResponse, existsUser);

    }
}
