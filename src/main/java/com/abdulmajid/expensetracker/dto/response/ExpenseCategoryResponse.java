package com.abdulmajid.expensetracker.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class ExpenseCategoryResponse {

    private Integer id;

    private String categoryName;

    private boolean defaultCategory;

    private Integer userId;
}