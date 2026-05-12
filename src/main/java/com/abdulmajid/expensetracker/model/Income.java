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
@Table(name = "incomes")

@Getter
@Setter
@NoArgsConstructor

public class Income extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "Amount is required")
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal amount;

    @NotBlank(message = "Receive mode is required")
    @Column(nullable = false)
    private String receiveMode;

    private String note;

    @NotNull(message = "Date is required")
    @Column(nullable = false)
    private LocalDate date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private IncomeCategory incomeCategory;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Income(
            BigDecimal amount,
            String receiveMode,
            String note,
            LocalDate date,
            IncomeCategory incomeCategory,
            User user
    ) {
        this.amount = amount;
        this.receiveMode = receiveMode;
        this.note = note;
        this.date = date;
        this.incomeCategory = incomeCategory;
        this.user = user;
    }
}