package com.abdulmajid.expensetracker.model;

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
@Table(name = "expenses")

@Getter
@Setter
@NoArgsConstructor

public class Expense extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "Amount is required")
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal amount;

    @NotBlank(message = "Payment mode is required")
    @Column(nullable = false)
    private String paymentMode;

    private String note;

    @NotNull(message = "Date is required")
    @Column(nullable = false)
    private LocalDate date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private ExpenseCategory expenseCategory;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Expense(
            BigDecimal amount,
            String paymentMode,
            String note,
            LocalDate date,
            ExpenseCategory expenseCategory,
            User user
    ) {
        this.amount = amount;
        this.paymentMode = paymentMode;
        this.note = note;
        this.date = date;
        this.expenseCategory = expenseCategory;
        this.user = user;
    }
}