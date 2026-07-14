package com.abdulmajid.expensetracker.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

public class UserRequest {

    @NotBlank(message = "Name should not be empty")
    private String name;

    @NotBlank(message = "Username should not be empty")
    private String userName;

    @Email(message = "Invalid email format")
    @NotBlank(message = "Email should not be empty")
    private String email;

    @NotBlank(message = "Phone number is required")
    @Pattern(
            regexp = "^\\d{10}$",
            message = "Invalid mobile number entered"
    )
    private String phone;

    @NotBlank(message = "Password is required")
    @Size(
            min = 8,
            max = 100,
            message = "Password must be at least 8 characters"
    )
    private String password;

    @NotBlank(message = "Confirm password is required")
    @Size(
            min = 8,
            max = 100,
            message = "Confirm password must be at least 8 characters"
    )
    private String confirmPassword;
}