package com.abdulmajid.expensetracker.service.impl;

import com.abdulmajid.expensetracker.dto.request.ExpenseRequest;
import com.abdulmajid.expensetracker.dto.response.DashboardSummaryResponse;
import com.abdulmajid.expensetracker.dto.response.ExpenseResponse;
import com.abdulmajid.expensetracker.enums.PaymentMode;
import com.abdulmajid.expensetracker.exception.custom.CategoryNotFoundException;
import com.abdulmajid.expensetracker.exception.custom.ExpenseNotFoundException;
import com.abdulmajid.expensetracker.exception.custom.InvalidArgumentException;
import com.abdulmajid.expensetracker.exception.custom.UserNotFoundException;
import com.abdulmajid.expensetracker.model.Expense;
import com.abdulmajid.expensetracker.model.ExpenseCategory;
import com.abdulmajid.expensetracker.model.User;
import com.abdulmajid.expensetracker.repository.ExpenseCategoryRepository;
import com.abdulmajid.expensetracker.repository.ExpenseRepository;
import com.abdulmajid.expensetracker.repository.IncomeRepository;
import com.abdulmajid.expensetracker.repository.UserRepository;
import com.abdulmajid.expensetracker.security.utils.SecurityUtils;
import com.abdulmajid.expensetracker.service.ExpenseService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor

public class ExpenseServiceImpl
        implements ExpenseService {

    private final ExpenseRepository expenseRepository;

    private final ExpenseCategoryRepository expenseCategoryRepository;

    private final IncomeRepository incomeRepository;

    private final UserRepository userRepository;

    @Override
    public List<ExpenseResponse> getCurrentUserExpenses() {
        String email = SecurityUtils.getCurrentUserEmail();
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
        return expenseRepository.findByUserId(user.getId())
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public ExpenseResponse createExpense(
            ExpenseRequest expenseRequest
    ) {

        // GET CURRENT LOGGED-IN USER EMAIL
        String email =
                SecurityUtils.getCurrentUserEmail();

        // FIND USER
        User user = userRepository
                .findByEmail(email)
                .orElseThrow(() ->
                        new UserNotFoundException(
                                "User not found"
                        )
                );

        // FIND CATEGORY
        ExpenseCategory expenseCategory =
                expenseCategoryRepository
                        .findById(
                                expenseRequest.getCategoryId()
                        )
                        .orElseThrow(() ->
                                new CategoryNotFoundException(
                                        "Category not found"
                                )
                        );

        // CREATE EXPENSE
        Expense expense = new Expense();

        expense.setAmount(
                expenseRequest.getAmount()
        );

        expense.setPaymentMode(
                expenseRequest.getPaymentMode()

        );

        expense.setNote(
                expenseRequest.getNote()
        );

        expense.setDate(
                expenseRequest.getDate()
        );

        expense.setExpenseCategory(
                expenseCategory
        );

        // jwt auth user
        expense.setUser(user);

        // SAVE
        Expense savedExpense =
                expenseRepository.save(expense);

        return mapToResponse(savedExpense);
    }


    //Ownership Validation

    @Override
    public ExpenseResponse getCurrentUserExpense(
            Integer expenseId
    ) {

        String email =
                SecurityUtils.getCurrentUserEmail();

        User user = userRepository
                .findByEmail(email)
                .orElseThrow(() ->
                        new UserNotFoundException(
                                "User not found"
                        )
                );

        Expense expense = expenseRepository
                .findById(expenseId)
                .orElseThrow(() ->
                        new ExpenseNotFoundException(
                                "Expense not found"
                        )
                );

        // OWNERSHIP VALIDATION
        if (!expense.getUser()
                .getId()
                .equals(user.getId())) {

            throw new InvalidArgumentException(
                    "You are not authorized to access this expense"
            );
        }

        return mapToResponse(expense);
    }


    //new update without user id

    @Override
    public ExpenseResponse updateExpense(

            Integer expenseId,

            ExpenseRequest expenseRequest
    ) {

        // GET CURRENT USER EMAIL
        String email =
                SecurityUtils.getCurrentUserEmail();

        // FIND CURRENT USER
        User user = userRepository
                .findByEmail(email)
                .orElseThrow(() ->
                        new UserNotFoundException(
                                "User not found"
                        )
                );

        // FIND EXPENSE
        Expense expense = expenseRepository
                .findById(expenseId)
                .orElseThrow(() ->
                        new ExpenseNotFoundException(
                                "Expense not found"
                        )
                );

        // OWNERSHIP VALIDATION
        if (!expense.getUser()
                .getId()
                .equals(user.getId())) {

            throw new InvalidArgumentException(
                    "You are not authorized to update this expense"
            );
        }

        // FIND CATEGORY
        ExpenseCategory expenseCategory =
                expenseCategoryRepository
                        .findById(
                                expenseRequest.getCategoryId()
                        )
                        .orElseThrow(() ->
                                new CategoryNotFoundException(
                                        "Category not found"
                                )
                        );

        // UPDATE FIELDS
        expense.setAmount(
                expenseRequest.getAmount()
        );

        expense.setPaymentMode(
                expenseRequest.getPaymentMode()
        );

        expense.setNote(
                expenseRequest.getNote()
        );

        expense.setDate(
                expenseRequest.getDate()
        );

        expense.setExpenseCategory(
                expenseCategory
        );

        // SAVE UPDATED EXPENSE
        Expense updatedExpense =
                expenseRepository.save(expense);

        return mapToResponse(updatedExpense);
    }

    @Override
    public ExpenseResponse deleteExpense(
            Integer expenseId
    ) {

        // GET CURRENT USER EMAIL
        String email =
                SecurityUtils.getCurrentUserEmail();

        // FIND CURRENT USER
        User user = userRepository
                .findByEmail(email)
                .orElseThrow(() ->
                        new UserNotFoundException(
                                "User not found"
                        )
                );

        // FIND EXPENSE
        Expense expense = expenseRepository
                .findById(expenseId)
                .orElseThrow(() ->
                        new ExpenseNotFoundException(
                                "Expense not found"
                        )
                );

        // OWNERSHIP VALIDATION
        if (!expense.getUser()
                .getId()
                .equals(user.getId())) {

            throw new InvalidArgumentException(
                    "You are not authorized to delete this expense"
            );
        }

        // MAP RESPONSE BEFORE DELETE
        ExpenseResponse response =
                mapToResponse(expense);

        // DELETE EXPENSE
        expenseRepository.delete(expense);

        return response;
    }

    // HELPER METHOD
    private ExpenseCategory getExpenseCategoryById(
            Integer categoryId
    ) {

        return expenseCategoryRepository.findById(categoryId)
                .orElseThrow(() ->
                        new CategoryNotFoundException(
                                "Expense category not found with ID: "
                                        + categoryId
                        )
                );
    }

    // MAPPER METHOD
    private ExpenseResponse mapToResponse(
            Expense expense
    ) {

        ExpenseResponse response =
                new ExpenseResponse();

        response.setId(
                expense.getId()
        );

        response.setAmount(
                expense.getAmount()
        );

        response.setPaymentMode(
                expense.getPaymentMode()
        );

        response.setNote(
                expense.getNote()
        );

        response.setDate(
                expense.getDate()
        );

        response.setCategoryId(
                expense.getExpenseCategory().getId()
        );

        response.setCategoryName(
                expense.getExpenseCategory().getCategoryName()
        );

        response.setUserId(
                expense.getUser().getId()
        );

        response.setCreatedAt(
                expense.getCreatedAt()
        );

        response.setUpdatedAt(
                expense.getUpdatedAt()
        );

        return response;
    }

    @Override
    public DashboardSummaryResponse
    getDashboardSummary() {

        String email =
                SecurityUtils
                        .getCurrentUserEmail();

        User user =
                userRepository
                        .findByEmail(email)
                        .orElseThrow(
                                () -> new UserNotFoundException(
                                        "User not found"
                                )
                        );

        BigDecimal totalExpenses =
                expenseRepository
                        .getTotalExpensesByUserId(
                                user.getId()
                        );

        BigDecimal totalIncome =
                incomeRepository
                        .getTotalIncomeByUserId(
                                user.getId()
                        );

        BigDecimal balance =
                totalIncome.subtract(
                        totalExpenses
                );

        return new DashboardSummaryResponse(

                totalExpenses,

                totalIncome,

                balance
        );
    }

    // pagination, sorting

    @Override
    public Page<ExpenseResponse>
    getCurrentUserExpenses(

            int page,

            int size,

            String sortBy,

            String sortDir,

            PaymentMode paymentMode,

            String keyword
    ) {

        // GET CURRENT USER EMAIL
        String email =
                SecurityUtils.getCurrentUserEmail();

        // FIND USER
        User user = userRepository
                .findByEmail(email)
                .orElseThrow(() ->
                        new UserNotFoundException(
                                "User not found"
                        )
                );

        // SORT
        Sort sort = sortDir.equalsIgnoreCase("asc")

                ? Sort.by(sortBy).ascending()

                : Sort.by(sortBy).descending();

        // PAGEABLE
        Pageable pageable =
                PageRequest.of(
                        page,
                        size,
                        sort
                );

        // DEFAULT KEYWORD
        if (keyword == null) {

            keyword = "";
        }

        // FETCH FILTERED DATA
        Page<Expense> expensePage;

        if (paymentMode != null
                && keyword != null
                && !keyword.isBlank()) {

            // PAYMENT MODE + KEYWORD
            expensePage = expenseRepository
                    .findByUserIdAndPaymentModeAndNoteContainingIgnoreCase(

                            user.getId(),

                            paymentMode,

                            keyword,

                            pageable
                    );

        } else if (paymentMode != null) {

            // ONLY PAYMENT MODE
            expensePage = expenseRepository
                    .findByUserIdAndPaymentMode(

                            user.getId(),

                            paymentMode,

                            pageable
                    );

        } else if (keyword != null
                && !keyword.isBlank()) {

            // ONLY KEYWORD
            expensePage = expenseRepository
                    .findByUserIdAndNoteContainingIgnoreCase(

                            user.getId(),

                            keyword,

                            pageable
                    );

        } else {

            // NO FILTER
            expensePage = expenseRepository
                    .findByUserId(

                            user.getId(),

                            pageable
                    );
        }

        return expensePage.map(
                this::mapToResponse
        );
    }

}