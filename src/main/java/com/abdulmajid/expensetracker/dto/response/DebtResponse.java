package com.abdulmajid.expensetracker.dto.response;

import com.abdulmajid.expensetracker.enums.DebtPriority;
import com.abdulmajid.expensetracker.enums.DebtStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor

public class DebtResponse {

    private Integer id;

    private BigDecimal amount;

    private String creditor;

    private String creditorName;

    private String debtor;

    private String debtorName;

    private LocalDate dueDate;

    private DebtStatus status;

    private DebtPriority priority;

    private Boolean recurring;

    private String category;

    private String note;

    private LocalDate date;

    private Integer userId;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}