package com.abdulmajid.expensetracker.dto.request;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor

public class IncomeRequest {

    @NotNull(message = "Amount cannot be null")
    @DecimalMin(value = "0.01", message = "Amount must be greater than 0")
    private BigDecimal amount;

    @NotBlank(message = "Receive mode is required")
    private String receiveMode;

    private String note;

    @PastOrPresent(message = "Date cannot be in the future")
    @NotNull(message = "Date cannot be null")
    private LocalDate date;

    @NotNull(message = "Category ID is required")
    @Min(value = 1, message = "Category ID must be a positive number")
    private Integer categoryId;

    private Integer userId;
}