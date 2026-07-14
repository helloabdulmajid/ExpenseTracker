package com.abdulmajid.expensetracker.dto.request;

import com.abdulmajid.expensetracker.enums.LoanStatus;
import com.abdulmajid.expensetracker.enums.LoanType;
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

public class LoanRequest {

    @NotNull(message = "Amount is required")
    @DecimalMin(
            value = "0.01",
            message = "Amount must be greater than zero"
    )
    private BigDecimal amount;

    @NotBlank(message = "Lender is required")
    private String lender;

    private String lenderName;

    @NotBlank(message = "Borrower is required")
    private String borrower;

    private String borrowerName;

    @NotNull(message = "Due date is required")
    @FutureOrPresent(
            message = "Due date must be today or future date"
    )
    private LocalDate dueDate;

    @NotNull(message = "Loan status is required")
    private LoanStatus status;

    @NotNull(message = "Loan type is required")
    private LoanType loanType;

    private Boolean recurring = false;

    private String note;

    @NotNull(message = "Date is required")
    private LocalDate date;

    private Integer userId;
}