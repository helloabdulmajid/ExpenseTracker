package com.abdulmajid.expensetracker.model;
import com.abdulmajid.expensetracker.enums.PaymentMode;
import com.abdulmajid.expensetracker.model.base.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity @Table(name = "expenses", indexes = {
    @Index(name = "idx_expenses_user_id", columnList = "user_id"),
    @Index(name = "idx_expenses_category_id", columnList = "category_id"),
    @Index(name = "idx_expenses_date", columnList = "date"),
    @Index(name = "idx_expenses_user_date", columnList = "user_id, date")
})
@Getter @Setter @NoArgsConstructor
public class Expense extends BaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull @DecimalMin("0.01") @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal amount;
    @NotNull @Enumerated(EnumType.STRING) @Column(nullable = false)
    private PaymentMode paymentMode;
    @Size(max = 500) private String note;
    @NotNull @Column(nullable = false)
    private LocalDate date;
    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "category_id", nullable = false)
    private ExpenseCategory expenseCategory;
    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "user_id", nullable = false)
    private User user;
    public Expense(BigDecimal amount, PaymentMode paymentMode, String note, LocalDate date,
                   ExpenseCategory expenseCategory, User user) {
        this.amount = amount; this.paymentMode = paymentMode; this.note = note;
        this.date = date; this.expenseCategory = expenseCategory; this.user = user;
    }
}
