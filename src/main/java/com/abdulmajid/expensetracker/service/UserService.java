package com.abdulmajid.expensetracker.service;

import com.abdulmajid.expensetracker.dto.request.UserRequest;
import com.abdulmajid.expensetracker.dto.response.UserResponse;

import java.util.List;

public interface UserService {

    UserResponse createUser(
            UserRequest userRequest
    );

    UserResponse getOneUser(
            Integer userId
    );

    List<UserResponse> getAllUsers();

    UserResponse updateUser(
            Integer userId,
            UserRequest userRequest
    );

    void deleteUser(
            Integer userId
    );

    UserResponse getCurrentUser();
}