package com.abdulmajid.expensetracker.controller;

import com.abdulmajid.expensetracker.dto.UserRequest;
import com.abdulmajid.expensetracker.dto.UserResponse;
import com.abdulmajid.expensetracker.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public UserResponse createUser(@RequestBody @Valid UserRequest userRequest) {
        return userService.createUser(userRequest);

    }

    @GetMapping("/{userId}")
    public UserResponse getOneUser(@PathVariable Integer userId) {
        return userService.getOneUser(userId);
    }


    @GetMapping("/allusers")
    public List<UserResponse> getAllUsers() {
        return userService.getAllUsers();
    }

    @PutMapping("/{userId}")
    public UserResponse updateUser(@PathVariable Integer userId, @RequestBody UserRequest userRequest) {
        return userService.updateUser(userId, userRequest);
    }

    @DeleteMapping("/delete/{userId}")
    public String deleteUser(@PathVariable Integer userId) {
        return userService.deleteUser(userId);
    }
}
