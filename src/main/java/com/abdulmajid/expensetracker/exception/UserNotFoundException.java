package com.abdulmajid.expensetracker.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message)
    {
        super(message);
    }
}
