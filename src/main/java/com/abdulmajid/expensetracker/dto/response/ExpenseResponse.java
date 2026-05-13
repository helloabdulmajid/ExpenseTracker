package com.abdulmajid.expensetracker.dto.response;

import com.abdulmajid.expensetracker.enums.PaymentMode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor

public class ExpenseResponse {

    private Integer id;

    private BigDecimal amount;

    private PaymentMode paymentMode;

    private String note;

    private LocalDate date;

    private Integer categoryId;

    private String categoryName;

    private Integer userId;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}