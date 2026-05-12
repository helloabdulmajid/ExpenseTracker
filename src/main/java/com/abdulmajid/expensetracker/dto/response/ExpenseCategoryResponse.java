package com.abdulmajid.expensetracker.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

public class ExpenseCategoryResponse {

    private Integer id;

    private String categoryName;

    private boolean defaultCategory;

    private Integer userId;
}