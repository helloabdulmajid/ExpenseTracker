package com.abdulmajid.expensetracker.exception.custom;

public class LoanNotFoundException extends RuntimeException {
    public LoanNotFoundException(String message) {
        super(message);
    }
}
