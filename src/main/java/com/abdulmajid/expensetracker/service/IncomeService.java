package com.abdulmajid.expensetracker.service;

import com.abdulmajid.expensetracker.dto.request.IncomeRequest;
import com.abdulmajid.expensetracker.dto.response.IncomeResponse;

import java.util.List;

public interface IncomeService {

    IncomeResponse createIncomeForUser(
            Integer userId,
            IncomeRequest incomeRequest
    );

    List<IncomeResponse> getAllIncomeForUser(
            Integer userId
    );

    IncomeResponse getIncome(
            Integer userId,
            Integer incomeId
    );

    List<IncomeResponse> getAllIncome();

    IncomeResponse updateIncome(
            Integer userId,
            Integer incomeId,
            IncomeRequest incomeRequest
    );

    void deleteIncome(
            Integer userId,
            Integer incomeId
    );
}