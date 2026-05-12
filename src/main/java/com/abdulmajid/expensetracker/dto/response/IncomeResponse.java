package com.abdulmajid.expensetracker.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor

public class IncomeResponse {

    private Integer id;

    private BigDecimal amount;

    private String receiveMode;

    private String note;

    private LocalDate date;

    private Integer categoryId;

    private String categoryName;

    private Integer userId;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}