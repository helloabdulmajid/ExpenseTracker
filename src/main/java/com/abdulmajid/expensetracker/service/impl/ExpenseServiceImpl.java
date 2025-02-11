package com.abdulmajid.expensetracker.service.impl;

import com.abdulmajid.expensetracker.dto.ExpenseCategoryResponse;
import com.abdulmajid.expensetracker.dto.ExpenseRequest;
import com.abdulmajid.expensetracker.dto.ExpenseResponse;
import com.abdulmajid.expensetracker.exception.custom.CategoryNotFoundException;
import com.abdulmajid.expensetracker.exception.custom.ExpenseNotFoundException;
import com.abdulmajid.expensetracker.exception.custom.UserNotFoundException;
import com.abdulmajid.expensetracker.model.Expense;
import com.abdulmajid.expensetracker.model.ExpenseCategory;
import com.abdulmajid.expensetracker.model.User;
import com.abdulmajid.expensetracker.repository.ExpenseCategoryRepository;
import com.abdulmajid.expensetracker.repository.ExpenseRepository;
import com.abdulmajid.expensetracker.repository.UserRepository;
import com.abdulmajid.expensetracker.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ExpenseServiceImpl implements ExpenseService {

    @Autowired
    private ExpenseRepository expenseRepository;
    @Autowired
    private ExpenseCategoryRepository expenseCategoryRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public ExpenseResponse createExpense(Integer userId, ExpenseRequest expenseRequest) {

        // get user from userId(param)
        User existsUser = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + userId));

        //get category from categoryId
        ExpenseCategory dbExpenseCategory = expenseCategoryRepository.findById(expenseRequest.getCategoryId())
                .orElseThrow(() -> new CategoryNotFoundException("Category not found with ID: " + expenseRequest.getCategoryId()));

        //create expense
        Expense expense = new Expense(expenseRequest.getAmount(),
                expenseRequest.getPaymentMode(),
                expenseRequest.getNote(),
                expenseRequest.getDay().toUpperCase(),
                expenseRequest.getDate(),
                expenseRequest.getCreatedAt(),
                expenseRequest.getUpdatedAt(),
                dbExpenseCategory,
                existsUser);


        // Save the expense in to db
        expenseRepository.save(expense);

        //get Total Expense from user table
        BigDecimal oldTotalExpense = existsUser.getExpense();
        BigDecimal newTotalExpense = oldTotalExpense.add(expenseRequest.getAmount());

        //add newTotalExpense in user table

        existsUser.setExpense(newTotalExpense);
        existsUser.setUpdatedAt(new Date());

        userRepository.save(existsUser);

        // Return ExpenseResponse (with category details)

        ExpenseCategoryResponse expenseCategoryResponse = new ExpenseCategoryResponse(dbExpenseCategory.getId(),
                dbExpenseCategory.getCategoryName(), dbExpenseCategory.isDefaultCategory());

        return new ExpenseResponse(expense.getId(), expense.getAmount(), expense.getPaymentMode(),
                expense.getNote(), expense.getDay(), expense.getDate(), expense.getCreatedAt(),
                expense.getUpdatedAt(), expenseCategoryResponse, existsUser);

    }

    @Override
    public List<Expense> getExpenseForUser(Integer userId) {
        User existsUser = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + userId));

        return expenseRepository.findByUserId(userId);
    }

    @Override
    public ExpenseResponse getExpense(Integer expenseId) {
        Expense existsExpense = expenseRepository.findById(expenseId)
                .orElseThrow(() -> new ExpenseNotFoundException("Expense Not found with Income Id:  " + expenseId));

        ExpenseCategory dbExpenseCategory = expenseCategoryRepository.findById(existsExpense.getCategory().getId())
                .orElseThrow(() -> new CategoryNotFoundException("Expense Category Not Found"));


        ExpenseCategoryResponse expenseCategoryResponse = new ExpenseCategoryResponse(dbExpenseCategory.getId(),
                dbExpenseCategory.getCategoryName(), dbExpenseCategory.isDefaultCategory());

        return new ExpenseResponse(existsExpense.getId(), existsExpense.getAmount(), existsExpense.getPaymentMode(),
                existsExpense.getNote(), existsExpense.getDay(), existsExpense.getDate(), existsExpense.getCreatedAt(),
                existsExpense.getUpdatedAt(), expenseCategoryResponse);
    }

    @Override
    public List<ExpenseResponse> getAllExpense() {
        List<Expense> allExpense = expenseRepository.findAll();
        ArrayList<ExpenseResponse> expenseResponses = new ArrayList<>();

        for (Expense expense : allExpense) {
            ExpenseResponse expenseResponse = new ExpenseResponse(expense.getId(), expense.getAmount(),
                    expense.getPaymentMode(), expense.getNote(), expense.getDay(), expense.getDate(),
                    expense.getCreatedAt(), expense.getUpdatedAt(),
                    new ExpenseCategoryResponse(expense.getCategory().getId(),
                            expense.getCategory().getCategoryName(),
                            expense.getCategory().isDefaultCategory()));

            expenseResponses.add(expenseResponse);
        }
        return expenseResponses;
    }

    @Override
    public String updateExpense(Integer userId, Integer expenseId, ExpenseRequest expenseRequest) {
        return "";
    }

    @Override
    public String deleteExpense(Integer userId, Integer expenseId) {
        return "";
    }


}
