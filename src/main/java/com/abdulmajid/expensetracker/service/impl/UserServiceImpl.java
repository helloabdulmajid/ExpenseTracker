package com.abdulmajid.expensetracker.service.impl;

import com.abdulmajid.expensetracker.dto.request.UserRequest;
import com.abdulmajid.expensetracker.dto.response.UserResponse;
import com.abdulmajid.expensetracker.exception.custom.InvalidArgumentException;
import com.abdulmajid.expensetracker.exception.custom.UserNotFoundException;
import com.abdulmajid.expensetracker.model.User;
import com.abdulmajid.expensetracker.repository.UserRepository;
import com.abdulmajid.expensetracker.security.utils.SecurityUtils;
import com.abdulmajid.expensetracker.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor

public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // CREATE USER
    @Override
    public UserResponse createUser(UserRequest userRequest) {

        validateUserRequest(userRequest);

        User user = new User(
                userRequest.getName(),
                userRequest.getUserName(),
                userRequest.getEmail(),
                passwordEncoder.encode(userRequest.getPassword()),
                userRequest.getPhone()
        );

        User savedUser = userRepository.save(user);

        return mapToResponse(savedUser);
    }

    // GET SINGLE USER
    @Override
    public UserResponse getOneUser(
            Integer userId
    ) {

        User user = getUserById(userId);

        return mapToResponse(user);
    }

    // GET ALL USERS
    @Override
    public List<UserResponse> getAllUsers() {

        return userRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    // UPDATE USER
    @Override
    public UserResponse updateUser(

            Integer userId,

            UserRequest userRequest
    ) {

        User user = getUserById(userId);

        user.setName(userRequest.getName());
        user.setUserName(userRequest.getUserName());
        user.setEmail(userRequest.getEmail());
        user.setPhone(userRequest.getPhone());

        User updatedUser = userRepository.save(user);

        return mapToResponse(updatedUser);
    }

    // DELETE USER
    @Override
    public void deleteUser(
            Integer userId
    ) {

        User user = getUserById(userId);

        userRepository.delete(user);
    }

    // HELPER METHOD
    private User getUserById(Integer userId) {

        return userRepository.findById(userId)
                .orElseThrow(() ->
                        new UserNotFoundException(
                                "User not found with ID: " + userId
                        )
                );
    }

    // HELPER METHOD
    private void validateUserRequest(
            UserRequest userRequest
    ) {

        if (!userRequest.getPassword()
                .equals(userRequest.getConfirmPassword())) {

            throw new InvalidArgumentException(
                    "Passwords do not match"
            );
        }

        if (userRepository.existsByUserName(
                userRequest.getUserName()
        )) {

            throw new InvalidArgumentException(
                    "Username already taken"
            );
        }

        if (userRepository.existsByEmail(
                userRequest.getEmail()
        )) {

            throw new InvalidArgumentException(
                    "Email already in use"
            );
        }

        if (userRepository.existsByPhone(
                userRequest.getPhone()
        )) {

            throw new InvalidArgumentException(
                    "Phone already in use"
            );
        }
    }

    @Override
    public UserResponse getCurrentUser() {

        String email =
                SecurityUtils.getCurrentUserEmail();

        User user = userRepository
                .findByEmail(email)
                .orElseThrow(() ->
                        new UserNotFoundException(
                                "User not found"
                        )
                );

        return mapToResponse(user);
    }

    // MAPPER METHOD
    private UserResponse mapToResponse(
            User user
    ) {

        UserResponse response = new UserResponse();

        response.setId(user.getId());
        response.setName(user.getName());
        response.setUserName(user.getUserName());
        response.setEmail(user.getEmail());
        response.setPhone(user.getPhone());

        response.setCreatedAt(
                user.getCreatedAt()
        );

        response.setUpdatedAt(
                user.getUpdatedAt()
        );

        return response;
    }
}