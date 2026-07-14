package com.abdulmajid.expensetracker.security.utils;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtils {

    private SecurityUtils() {
    }

    public static String getCurrentUserEmail() {

        Authentication authentication =

                SecurityContextHolder
                        .getContext()
                        .getAuthentication();

        if (authentication == null
                || authentication.getName() == null) {

            throw new com.abdulmajid.expensetracker.exception.custom.UserNotFoundException("User not authenticated");
        }

        return authentication.getName();
    }
}