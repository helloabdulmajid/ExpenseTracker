package com.abdulmajid.expensetracker.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String userName;
    private String email;
    private String password;
    private String phone;
    private Integer expense;
    private Integer income;
    private String debt;
    private Integer loan;
    private Date createdAt = new Date();
    private Date updated_at;

}
