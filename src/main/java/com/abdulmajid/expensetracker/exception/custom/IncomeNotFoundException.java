package com.abdulmajid.expensetracker.exception.custom;

public class IncomeNotFoundException extends RuntimeException {
    public IncomeNotFoundException(String message) {
        super(message);
    }
}
