package com.abdulmajid.expensetracker.exception.handler;

import com.abdulmajid.expensetracker.exception.custom.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice

public class GlobalExceptionHandler {

    // VALIDATION ERRORS
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>>
    handleValidationExceptions(
            MethodArgumentNotValidException ex
    ) {

        Map<String, String> errors =
                new HashMap<>();

        for (FieldError error :
                ex.getBindingResult().getFieldErrors()) {

            errors.put(
                    error.getField(),
                    error.getDefaultMessage()
            );
        }

        return ResponseEntity
                .badRequest()
                .body(errors);
    }

    // USER NOT FOUND
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ApiErrorResponse>
    handleUserNotFoundException(
            UserNotFoundException ex
    ) {

        return buildErrorResponse(
                HttpStatus.NOT_FOUND,
                ex.getMessage()
        );
    }

    // CATEGORY ERROR
    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<ApiErrorResponse>
    handleCategoryException(
            CategoryNotFoundException ex
    ) {

        return buildErrorResponse(
                HttpStatus.BAD_REQUEST,
                ex.getMessage()
        );
    }

    // EXPENSE ERROR
    @ExceptionHandler(ExpenseNotFoundException.class)
    public ResponseEntity<ApiErrorResponse>
    handleExpenseException(
            ExpenseNotFoundException ex
    ) {

        return buildErrorResponse(
                HttpStatus.NOT_FOUND,
                ex.getMessage()
        );
    }

    // INCOME ERROR
    @ExceptionHandler(IncomeNotFoundException.class)
    public ResponseEntity<ApiErrorResponse>
    handleIncomeException(
            IncomeNotFoundException ex
    ) {

        return buildErrorResponse(
                HttpStatus.NOT_FOUND,
                ex.getMessage()
        );
    }

    // DEBT ERROR
    @ExceptionHandler(DebtNotFoundException.class)
    public ResponseEntity<ApiErrorResponse>
    handleDebtException(
            DebtNotFoundException ex
    ) {

        return buildErrorResponse(
                HttpStatus.NOT_FOUND,
                ex.getMessage()
        );
    }

    // LOAN ERROR
    @ExceptionHandler(LoanNotFoundException.class)
    public ResponseEntity<ApiErrorResponse>
    handleLoanException(
            LoanNotFoundException ex
    ) {

        return buildErrorResponse(
                HttpStatus.NOT_FOUND,
                ex.getMessage()
        );
    }

    // ENUM ERROR
    @ExceptionHandler(InvalidEnumException.class)
    public ResponseEntity<ApiErrorResponse>
    handleEnumException(
            InvalidEnumException ex
    ) {

        return buildErrorResponse(
                HttpStatus.BAD_REQUEST,
                ex.getMessage()
        );
    }

    // INVALID ARGUMENT
    @ExceptionHandler(InvalidArgumentException.class)
    public ResponseEntity<ApiErrorResponse>
    handleInvalidArgumentException(
            InvalidArgumentException ex
    ) {

        return buildErrorResponse(
                HttpStatus.BAD_REQUEST,
                ex.getMessage()
        );
    }

    // GLOBAL ERROR
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiErrorResponse>
    handleGlobalException(
            Exception ex
    ) {

        ex.printStackTrace();

        return buildErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "Something went wrong. Please try again later."
        );
    }

    // COMMON BUILDER METHOD
    private ResponseEntity<ApiErrorResponse>
    buildErrorResponse(

            HttpStatus status,

            String message
    ) {

        ApiErrorResponse errorResponse =
                new ApiErrorResponse(
                        LocalDateTime.now(),
                        status.value(),
                        status.getReasonPhrase(),
                        message
                );

        return ResponseEntity
                .status(status)
                .body(errorResponse);
    }
}