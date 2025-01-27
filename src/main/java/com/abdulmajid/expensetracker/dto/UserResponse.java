package com.abdulmajid.expensetracker.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Data
@Builder
@Getter
@Setter
public class UserResponse {
    private Integer id;
    private String name;
    private String userName;
    private String email;
    private String phone;
    private Integer expense;
    private Integer income;
    private String debt;
    private Integer loan;
    private Date createdAt ;
    private Date updated_at;
}
