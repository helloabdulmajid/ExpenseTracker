package com.abdulmajid.expensetracker.exception.custom;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message)
    {
        super(message);
    }
}
