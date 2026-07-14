package com.abdulmajid.expensetracker.dto.request;
import com.abdulmajid.expensetracker.enums.DebtCategory;
import com.abdulmajid.expensetracker.enums.DebtPriority;
import com.abdulmajid.expensetracker.enums.DebtStatus;
import jakarta.validation.constraints.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter @Setter @NoArgsConstructor
public class DebtRequest {
    @NotNull @DecimalMin("0.01") private BigDecimal amount;
    @NotBlank private String creditor;
    @NotBlank private String creditorName;
    @NotBlank private String debtor;
    @NotBlank private String debtorName;
    @NotNull @FutureOrPresent private LocalDate dueDate;
    @NotNull private DebtStatus status;
    @NotNull private DebtPriority priority;
    @NotNull private Boolean recurring;
    @NotNull private DebtCategory category;
    private String note;
    @NotNull private LocalDate date;
    private Integer userId;
}
