package com.abdulmajid.expensetracker.security.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

@Component
@RequiredArgsConstructor

public class JwtAuthenticationFilter
        extends OncePerRequestFilter {

    private final JwtService jwtService;

    @Override
    protected void doFilterInternal(

            HttpServletRequest request,

            HttpServletResponse response,

            FilterChain filterChain

    ) throws ServletException, IOException {

        // GET AUTH HEADER
        final String authHeader =
                request.getHeader("Authorization");

        // CHECK HEADER EXISTS
        if (authHeader == null
                || !authHeader.startsWith("Bearer ")) {

            filterChain.doFilter(
                    request,
                    response
            );

            return;
        }

        // EXTRACT TOKEN
        String jwtToken =
                authHeader.substring(7);

        // EXTRACT EMAIL
        String userEmail =
                jwtService.extractEmail(jwtToken);

        // CHECK USER NOT ALREADY AUTHENTICATED
        if (userEmail != null
                &&
                SecurityContextHolder
                        .getContext()
                        .getAuthentication() == null) {

            // CREATE USER DETAILS
            UserDetails userDetails =
                    new User(
                            userEmail,
                            "",
                            Collections.emptyList()
                    );

            // VALIDATE TOKEN
            if (jwtService.isTokenValid(
                    jwtToken,
                    userEmail
            )) {

                // CREATE AUTH TOKEN
                UsernamePasswordAuthenticationToken authToken =
                        new UsernamePasswordAuthenticationToken(

                                userDetails,

                                null,

                                userDetails.getAuthorities()
                        );

                authToken.setDetails(

                        new WebAuthenticationDetailsSource()

                                .buildDetails(request)
                );

                // SET AUTHENTICATION
                SecurityContextHolder
                        .getContext()
                        .setAuthentication(authToken);
            }
        }

        // CONTINUE REQUEST
        filterChain.doFilter(
                request,
                response
        );
    }
}