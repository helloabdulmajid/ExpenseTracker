package com.abdulmajid.expensetracker.exception.custom;

public class DebtNotFoundException extends RuntimeException {
    public DebtNotFoundException(String message) {
        super(message);
    }
}
