package com.abdulmajid.expensetracker.model;

import com.abdulmajid.expensetracker.enums.LoanStatus;
import com.abdulmajid.expensetracker.enums.LoanType;
import com.abdulmajid.expensetracker.model.base.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "loans")

@Getter
@Setter
@NoArgsConstructor

public class Loan extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "Amount is required")
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal amount;

    @NotBlank(message = "Lender is required")
    private String lender;

    private String lenderName;

    @NotBlank(message = "Borrower is required")
    private String borrower;

    private String borrowerName;

    @NotNull(message = "Due date is required")
    private LocalDate dueDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private LoanStatus status;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private LoanType loanType;

    @Column(nullable = false)
    private Boolean recurring = false;

    private String note;

    @NotNull(message = "Date is required")
    private LocalDate date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Loan(
            BigDecimal amount,
            String lender,
            String lenderName,
            String borrower,
            String borrowerName,
            LocalDate dueDate,
            LoanStatus status,
            LoanType loanType,
            Boolean recurring,
            String note,
            LocalDate date,
            User user
    ) {
        this.amount = amount;
        this.lender = lender;
        this.lenderName = lenderName;
        this.borrower = borrower;
        this.borrowerName = borrowerName;
        this.dueDate = dueDate;
        this.status = status;
        this.loanType = loanType;
        this.recurring = recurring;
        this.note = note;
        this.date = date;
        this.user = user;
    }
}