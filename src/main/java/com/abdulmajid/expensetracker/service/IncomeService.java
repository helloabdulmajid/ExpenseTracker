package com.abdulmajid.expensetracker.service;

import com.abdulmajid.expensetracker.dto.IncomeRequest;
import com.abdulmajid.expensetracker.dto.IncomeResponse;
import com.abdulmajid.expensetracker.model.Income;

import java.util.List;

public interface IncomeService {
    public IncomeResponse createIncomeForUser(Integer userId, IncomeRequest incomeRequest);

    List<Income> getIncomeForUser(Integer userId);

    String updateIncome(Integer userId, Integer incomeId, IncomeRequest incomeRequest);

    IncomeResponse getIncome(Integer incomeId);

    String deleteIncome(Integer userId, Integer incomeId);

    List<IncomeResponse> getAllIncome();
}
