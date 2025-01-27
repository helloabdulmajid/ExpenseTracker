package com.abdulmajid.expensetracker.service.impl;

import com.abdulmajid.expensetracker.dto.UserRequest;
import com.abdulmajid.expensetracker.dto.UserResponse;
import com.abdulmajid.expensetracker.exception.InvalidArgumentException;
import com.abdulmajid.expensetracker.model.User;
import com.abdulmajid.expensetracker.repository.UserRepository;
import com.abdulmajid.expensetracker.service.UserService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Override
    public UserResponse createUser(UserRequest userRequest) {
        if (!userRequest.getPassword().equals(userRequest.getcPassword())) {
            throw new InvalidArgumentException("Password Must be Same ");
        }
        if (userRepository.existsByUserName(userRequest.getUserName())) {
            throw new InvalidArgumentException("UserName Already Taken..Try Another One..");
        }
        if (userRepository.existsByEmail(userRequest.getEmail())) {
            throw new InvalidArgumentException("Email Already in Use..Login please..");
        }
        if (userRepository.existsByPhone(userRequest.getPhone())) {
            throw new InvalidArgumentException("Phone Already in Use....Try Another One.");
        }

       User user=userRepository.save(
        User.builder().name(userRequest.getName())
                .userName(userRequest.getUserName())
                .email(userRequest.getEmail())
                .password(userRequest.getPassword())
                .phone(userRequest.getPhone())
                .debt(userRequest.getDebt())
                .createdAt(new Date()).build()
             );

        return UserResponse.builder().id(user.getId())
                .name(user.getName())
                .userName(user.getUserName())
                .email(user.getEmail())
                .phone(user.getPhone())
                .createdAt(user.getCreatedAt())
                .updated_at(user.getUpdated_at()).build();

    }

    @Override
    public UserResponse getOneUser(Integer userId) {
        return null;
    }

    @Override
    public List<UserResponse> getAllUsers() {
        return null;
    }

    @Override
    public UserResponse updateUser(Integer userId, UserRequest userRequest) {
        return null;
    }
}
