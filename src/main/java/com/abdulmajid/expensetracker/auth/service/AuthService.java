package com.abdulmajid.expensetracker.auth.service;

import com.abdulmajid.expensetracker.auth.dto.AuthResponse;
import com.abdulmajid.expensetracker.auth.dto.LoginRequest;

public interface AuthService {

    AuthResponse login(
            LoginRequest loginRequest
    );
}