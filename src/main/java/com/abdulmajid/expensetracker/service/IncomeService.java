package com.abdulmajid.expensetracker.service;

import com.abdulmajid.expensetracker.dto.request.IncomeRequest;
import com.abdulmajid.expensetracker.dto.response.IncomeResponse;

import java.util.List;

public interface IncomeService {


    IncomeResponse createIncome(
            IncomeRequest incomeRequest
    );

    List<IncomeResponse> getCurrentUserIncomes();

    IncomeResponse getCurrentUserIncome(
            Integer incomeId
    );

    IncomeResponse updateIncome(
            Integer incomeId,
            IncomeRequest incomeRequest
    );

    IncomeResponse deleteIncome(
            Integer incomeId
    );

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