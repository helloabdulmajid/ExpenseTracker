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
    public List<ExpenseResponse> getExpenseForUser(Integer userId) {
        User existsUser = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + userId));

        List<Expense> expenseList = expenseRepository.findByUserId(userId);
        ArrayList<ExpenseResponse> expenseResponses = new ArrayList<>();

        for (Expense expense : expenseList) {
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

        User existsUser = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + userId));

        //get Expense from expenseId
        Expense existsExpense = expenseRepository.findById(expenseId)
                .orElseThrow(() -> new ExpenseNotFoundException("Expense Not found with Expense Id:  " + expenseId));

        BigDecimal oldAmount = existsExpense.getAmount();
        BigDecimal newAmount = expenseRequest.getAmount();

        ExpenseCategory dbExpenseCategory = expenseCategoryRepository.findById(existsExpense.getCategory().getId())
                .orElseThrow(() -> new CategoryNotFoundException("Expense Category not found " + existsExpense.getCategory().getId()));


        // If new amount is the same as old, just update the Expense all records and return
        if (newAmount.compareTo(oldAmount) == 0) {
            existsExpense.setCategory(dbExpenseCategory);
            existsExpense.setNote(expenseRequest.getNote());
            existsExpense.setUpdatedAt(new Date());

            expenseRepository.save(existsExpense);
            return "Expense Updated SuccessFully , Expense Id :" + expenseId; //  Exit the function early (no need to update user's Expense)
        }

        // if NewAmount is greater than old Amount → Add the difference to user table

        if (newAmount.compareTo(oldAmount) > 0) {
            BigDecimal difference = newAmount.subtract(oldAmount);
            existsUser.setExpense(existsUser.getExpense().add(difference));
        } else {
            // if New amount is smaller → Subtract the difference
            BigDecimal difference = oldAmount.subtract(newAmount);

            if (existsUser.getExpense().subtract(difference).compareTo(BigDecimal.ZERO) < 0) {
                throw new ExpenseNotFoundException("Not enough balance to update this Expense");
            }

            existsUser.setExpense(existsUser.getExpense().subtract(difference));
        }

        // Update the Expense record
        existsExpense.setAmount(newAmount);
        existsExpense.setCategory(dbExpenseCategory);
        existsExpense.setNote(expenseRequest.getNote());
        existsExpense.setUpdatedAt(new Date());

        // Save updates
        expenseRepository.save(existsExpense);
        existsUser.setUpdatedAt(new Date());
        userRepository.save(existsUser);

        return "Expense Updated SuccessFully , Expense Id :" + expenseId;
    }

    @Override
    public String deleteExpense(Integer userId, Integer expenseId) {
        User existsUser = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + userId));

        Expense existsExpense = expenseRepository.findById(expenseId)
                .orElseThrow(() -> new ExpenseNotFoundException("Expense Not found with Expense Id:  " + expenseId));

        BigDecimal expenseAmount = existsExpense.getAmount();

        try {
            existsUser.setExpense(existsUser.getExpense().subtract(expenseAmount));

            // Remove Expense record
            expenseRepository.delete(existsExpense);
            userRepository.save(existsUser);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Deleted Expense Id : -> " + expenseId;
    }


}
