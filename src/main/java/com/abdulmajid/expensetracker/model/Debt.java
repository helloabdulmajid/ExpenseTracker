package com.abdulmajid.expensetracker.model;

import com.abdulmajid.expensetracker.enums.DebtPriority;
import com.abdulmajid.expensetracker.enums.DebtStatus;
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
@Table(name = "debts")

@Getter
@Setter
@NoArgsConstructor

public class Debt extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "Amount is required")
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal amount;

    @NotBlank(message = "Creditor is required")
    private String creditor;

    private String creditorName;

    @NotBlank(message = "Debtor is required")
    private String debtor;

    private String debtorName;

    @NotNull(message = "Due date is required")
    private LocalDate dueDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DebtStatus status;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DebtPriority priority;

    @Column(nullable = false)
    private Boolean recurring = false;

    private String category;

    private String note;

    @NotNull(message = "Date is required")
    private LocalDate date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Debt(
            BigDecimal amount,
            String creditor,
            String creditorName,
            String debtor,
            String debtorName,
            LocalDate dueDate,
            DebtStatus status,
            DebtPriority priority,
            Boolean recurring,
            String category,
            String note,
            LocalDate date,
            User user
    ) {
        this.amount = amount;
        this.creditor = creditor;
        this.creditorName = creditorName;
        this.debtor = debtor;
        this.debtorName = debtorName;
        this.dueDate = dueDate;
        this.status = status;
        this.priority = priority;
        this.recurring = recurring;
        this.category = category;
        this.note = note;
        this.date = date;
        this.user = user;
    }
}