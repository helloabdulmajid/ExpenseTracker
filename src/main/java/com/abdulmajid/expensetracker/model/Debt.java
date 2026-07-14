package com.abdulmajid.expensetracker.model;
import com.abdulmajid.expensetracker.enums.DebtCategory;
import com.abdulmajid.expensetracker.enums.DebtPriority;
import com.abdulmajid.expensetracker.enums.DebtStatus;
import com.abdulmajid.expensetracker.model.base.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity @Table(name = "debts", indexes = {
    @Index(name = "idx_debts_user_id", columnList = "user_id"),
    @Index(name = "idx_debts_due_date", columnList = "due_date")
})
@Getter @Setter @NoArgsConstructor
public class Debt extends BaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull @DecimalMin("0.01") @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal amount;
    @NotBlank @Size(max = 100) private String creditor;
    @Size(max = 100) private String creditorName;
    @NotBlank @Size(max = 100) private String debtor;
    @Size(max = 100) private String debtorName;
    @NotNull private LocalDate dueDate;
    @NotNull @Enumerated(EnumType.STRING) @Column(nullable = false) private DebtStatus status;
    @NotNull @Enumerated(EnumType.STRING) @Column(nullable = false) private DebtPriority priority;
    @Column(nullable = false) private Boolean recurring = false;
    @NotNull @Enumerated(EnumType.STRING) @Column(nullable = false) private DebtCategory category;
    @Size(max = 500) private String note;
    @NotNull private LocalDate date;
    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "user_id", nullable = false)
    private User user;
    public Debt(BigDecimal amount, String creditor, String creditorName, String debtor, String debtorName,
                LocalDate dueDate, DebtStatus status, DebtPriority priority, Boolean recurring,
                DebtCategory category, String note, LocalDate date, User user) {
        this.amount = amount; this.creditor = creditor; this.creditorName = creditorName;
        this.debtor = debtor; this.debtorName = debtorName; this.dueDate = dueDate;
        this.status = status; this.priority = priority; this.recurring = recurring;
        this.category = category; this.note = note; this.date = date; this.user = user;
    }
}
