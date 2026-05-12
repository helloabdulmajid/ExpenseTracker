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

public class ExpenseRequest {

    @NotNull(message = "Amount cannot be empty")
    @DecimalMin(value = "1.0", message = "Expense amount should be greater than 0")
    @DecimalMax(value = "1000000.0", message = "Expense amount should be lower than 1M")
    private BigDecimal amount;

    @NotBlank(message = "Please choose a payment mode")
    @Pattern(
            regexp = "CASH|CARD|UPI",
            message = "Invalid payment mode. Choose CASH, CARD, or UPI"
    )
    private String paymentMode;

    private String note;

    @PastOrPresent(message = "Date cannot be in the future")
    @NotNull(message = "Date cannot be null")
    private LocalDate date;

    @NotNull(message = "Category ID is required")
    @Min(value = 1, message = "Category ID must be a positive number")
    private Integer categoryId;

    private Integer userId;
}