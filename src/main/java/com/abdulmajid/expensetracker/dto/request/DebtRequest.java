package com.abdulmajid.expensetracker.dto.request;

import com.abdulmajid.expensetracker.enums.DebtPriority;
import com.abdulmajid.expensetracker.enums.DebtStatus;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor

public class DebtRequest {

    @NotNull(message = "Amount cannot be null")
    @DecimalMin(value = "0.01", message = "Amount must be greater than zero")
    private BigDecimal amount;

    @NotBlank(message = "Creditor is required")
    private String creditor;

    @NotBlank(message = "Creditor name is required")
    private String creditorName;

    @NotBlank(message = "Debtor is required")
    private String debtor;

    @NotBlank(message = "Debtor name is required")
    private String debtorName;

    @NotNull(message = "Due date is required")
    @FutureOrPresent(message = "Due date must be today or in the future")
    private LocalDate dueDate;

    @NotNull(message = "Status is required")
    private DebtStatus status;

    @NotNull(message = "Priority is required")
    private DebtPriority priority;

    @NotNull(message = "Recurring flag is required")
    private Boolean recurring;

    @NotBlank(message = "Category is required")
    private String category;

    private String note;

    @NotNull(message = "Date is required")
    private LocalDate date;

    private Integer userId;
}