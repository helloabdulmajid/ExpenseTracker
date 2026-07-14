package com.abdulmajid.expensetracker.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class DashboardSummaryResponse {

    private BigDecimal totalExpenses;

    private BigDecimal totalIncome;

    private BigDecimal balance;
}