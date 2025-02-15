package com.abdulmajid.expensetracker.exception.custom;

public class InvalidEnumException extends RuntimeException {
    public InvalidEnumException(String message) {
        super(message);
    }
}
