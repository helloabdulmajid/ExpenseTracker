package com.abdulmajid.expensetracker.exception.custom;

public class InvalidArgumentException extends RuntimeException {
    public InvalidArgumentException(String message) {
        super(message);
    }
}
