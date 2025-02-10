package com.abdulmajid.expensetracker.service.impl;

import com.abdulmajid.expensetracker.dto.UserRequest;
import com.abdulmajid.expensetracker.dto.UserResponse;
import com.abdulmajid.expensetracker.exception.custom.InvalidArgumentException;
import com.abdulmajid.expensetracker.exception.custom.UserNotFoundException;
import com.abdulmajid.expensetracker.model.User;
import com.abdulmajid.expensetracker.repository.UserRepository;
import com.abdulmajid.expensetracker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
        User user = new User(userRequest.getName(),
                userRequest.getUserName(),
                userRequest.getEmail(),
                userRequest.getPassword(),
                userRequest.getPhone());
        userRepository.save(user);


        return new UserResponse(user.getId(),
                user.getName(),
                user.getUserName(),
                user.getPhone(),
                user.getEmail(),
                user.getCreatedAt(),
                user.getUpdatedAt());


//       User user=userRepository.save(
//        User.builder().name(userRequest.getName())
//                .userName(userRequest.getUserName())
//                .email(userRequest.getEmail())
//                .password(userRequest.getPassword())
//                .phone(userRequest.getPhone())
//                .debt(userRequest.getDebt())
//                .createdAt(new Date()).build()
//             );

//        return UserResponse.builder().id(user.getId())
//                .name(user.getName())
//                .userName(user.getUserName())
//                .email(user.getEmail())
//                .phone(user.getPhone())
//                .createdAt(user.getCreatedAt())
//                .updated_at(user.getUpdated_at()).build();

    }

    @Override
    public UserResponse getOneUser(Integer userId) {

        Optional<User> existsUser = userRepository.findById(userId);
        if (existsUser.isPresent()) {
            return new UserResponse(existsUser);
        }
        throw new UserNotFoundException("User With ID " + userId + " is Not Found");


    }

    @Override
    public List<UserResponse> getAllUsers() {

        return userRepository.findAll().stream()
                .map(user -> new UserResponse(user.getId(),
                        user.getName(),
                        user.getUserName(),
                        user.getEmail(),
                        user.getPhone(),
                        user.getExpense(),
                        user.getIncome(),
                        user.getDebt(),
                        user.getLoan(),
                        user.getCreatedAt(),
                        user.getUpdatedAt()))
                .collect(Collectors.toList());


    }

    @Override
    public UserResponse updateUser(Integer userId, UserRequest userRequest) {
        Optional<User> existsUser = userRepository.findById(userId);
        if (existsUser.isPresent()) {
            User user = existsUser.get();
            user.setName(userRequest.getName());
            user.setUserName(userRequest.getUserName());
            user.setUpdatedAt(new Date());
            return new UserResponse(Optional.of(user));
        }
        throw new UserNotFoundException("User With ID " + userId + " is Not Found");
    }
}
