package com.abdulmajid.expensetracker.model;

import com.abdulmajid.expensetracker.model.base.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users")

@Getter
@Setter
@NoArgsConstructor

public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Name is required")
    @Column(nullable = false)
    private String name;

    @NotBlank(message = "Username is required")
    @Column(nullable = false, unique = true)
    private String userName;

    @Email(message = "Invalid email format")
    @NotBlank(message = "Email is required")
    @Column(nullable = false, unique = true)
    private String email;

    @NotBlank(message = "Password is required")
    @Column(nullable = false)
    private String password;

    private String phone;

    public User(
            String name,
            String userName,
            String email,
            String password,
            String phone
    ) {
        this.name = name;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.phone = phone;
    }
}