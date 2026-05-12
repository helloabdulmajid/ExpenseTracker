package com.abdulmajid.expensetracker.dto.response;

import com.abdulmajid.expensetracker.enums.LoanStatus;
import com.abdulmajid.expensetracker.enums.LoanType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor

public class LoanResponse {

    private Integer id;

    private BigDecimal amount;

    private String lender;

    private String lenderName;

    private String borrower;

    private String borrowerName;

    private LocalDate dueDate;

    private LoanStatus status;

    private LoanType loanType;

    private Boolean recurring;

    private String note;

    private LocalDate date;

    private Integer userId;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}