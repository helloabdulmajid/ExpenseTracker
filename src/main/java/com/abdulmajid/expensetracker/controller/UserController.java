package com.abdulmajid.expensetracker.controller;

import com.abdulmajid.expensetracker.dto.request.UserRequest;
import com.abdulmajid.expensetracker.dto.response.UserResponse;
import com.abdulmajid.expensetracker.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Tag(
        name = "User APIs",
        description = "Operations related to users"
)
public class UserController {

    private final UserService userService;

    // CREATE USER
    @PostMapping("/signup")
    @Operation(
            summary = "Create user",
            description = "Register a new user"
    )
    public ResponseEntity<UserResponse> createUser(

            @Valid @RequestBody UserRequest userRequest
    ) {

        UserResponse response =
                userService.createUser(userRequest);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    // GET SINGLE USER
    @GetMapping("/{userId}")
    public ResponseEntity<UserResponse> getOneUser(

            @PathVariable Integer userId
    ) {

        return ResponseEntity.ok(
                userService.getOneUser(userId)
        );
    }

    // GET ALL USERS
    @GetMapping
    public ResponseEntity<List<UserResponse>> getAllUsers() {

        return ResponseEntity.ok(
                userService.getAllUsers()
        );
    }

    // UPDATE USER
    @PutMapping("/{userId}")
    public ResponseEntity<UserResponse> updateUser(

            @PathVariable Integer userId,

            @Valid @RequestBody UserRequest userRequest
    ) {

        return ResponseEntity.ok(
                userService.updateUser(userId, userRequest)
        );
    }

    // DELETE USER
    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(

            @PathVariable Integer userId
    ) {

        userService.deleteUser(userId);

        return ResponseEntity.noContent().build();
    }
}