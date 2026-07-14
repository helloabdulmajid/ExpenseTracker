package com.abdulmajid.expensetracker.auth.service;

import com.abdulmajid.expensetracker.auth.dto.AuthResponse;
import com.abdulmajid.expensetracker.auth.dto.LoginRequest;
import com.abdulmajid.expensetracker.exception.custom.InvalidArgumentException;
import com.abdulmajid.expensetracker.model.User;
import com.abdulmajid.expensetracker.repository.UserRepository;
import com.abdulmajid.expensetracker.security.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class AuthServiceImpl
        implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;


    @Override
    public AuthResponse login(
            LoginRequest loginRequest
    ) {

        // FIND USER BY EMAIL
        User user = userRepository
                .findByEmail(
                        loginRequest.getEmail()
                )
                .orElseThrow(() ->
                        new InvalidArgumentException(
                                "Invalid email or password"
                        )
                );

        // VERIFY PASSWORD
        boolean passwordMatches =
                passwordEncoder.matches(

                        loginRequest.getPassword(),

                        user.getPassword()
                );

        if (!passwordMatches) {

            throw new InvalidArgumentException(
                    "Invalid email or password"
            );
        }

        //  RESPONSE
        String token =
                jwtService.generateToken(
                        user.getEmail()
                );

        return new AuthResponse(

                token,

                "User logged in successfully"
        );
    }
}