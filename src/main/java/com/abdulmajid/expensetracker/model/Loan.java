package com.abdulmajid.expensetracker.model;
import com.abdulmajid.expensetracker.enums.LoanStatus;
import com.abdulmajid.expensetracker.enums.LoanType;
import com.abdulmajid.expensetracker.model.base.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity @Table(name = "loans", indexes = {
    @Index(name = "idx_loans_user_id", columnList = "user_id"),
    @Index(name = "idx_loans_due_date", columnList = "due_date")
})
@Getter @Setter @NoArgsConstructor
public class Loan extends BaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull @DecimalMin("0.01") @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal amount;
    @NotBlank @Size(max = 100) private String lender;
    @Size(max = 100) private String lenderName;
    @NotBlank @Size(max = 100) private String borrower;
    @Size(max = 100) private String borrowerName;
    @NotNull private LocalDate dueDate;
    @NotNull @Enumerated(EnumType.STRING) @Column(nullable = false)
    private LoanStatus status;
    @NotNull @Enumerated(EnumType.STRING) @Column(nullable = false)
    private LoanType loanType;
    @Column(nullable = false) private Boolean recurring = false;
    @Size(max = 500) private String note;
    @NotNull private LocalDate date;
    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "user_id", nullable = false)
    private User user;
    public Loan(BigDecimal amount, String lender, String lenderName, String borrower, String borrowerName,
                LocalDate dueDate, LoanStatus status, LoanType loanType, Boolean recurring,
                String note, LocalDate date, User user) {
        this.amount = amount; this.lender = lender; this.lenderName = lenderName;
        this.borrower = borrower; this.borrowerName = borrowerName; this.dueDate = dueDate;
        this.status = status; this.loanType = loanType; this.recurring = recurring;
        this.note = note; this.date = date; this.user = user;
    }
}
