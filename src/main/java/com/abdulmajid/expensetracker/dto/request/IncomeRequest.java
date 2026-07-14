package com.abdulmajid.expensetracker.dto.request;
import com.abdulmajid.expensetracker.enums.ReceiveMode;
import jakarta.validation.constraints.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter @Setter @NoArgsConstructor
public class IncomeRequest {
    @NotNull @DecimalMin("0.01") private BigDecimal amount;
    @NotNull private ReceiveMode receiveMode;
    private String note;
    @PastOrPresent @NotNull private LocalDate date;
    @NotNull @Min(1) private Integer categoryId;
    private Integer userId;
}
