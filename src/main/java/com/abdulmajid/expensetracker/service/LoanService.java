package com.abdulmajid.expensetracker.service;

import com.abdulmajid.expensetracker.dto.LoanRequest;
import com.abdulmajid.expensetracker.dto.LoanResponse;

import java.util.List;

public interface LoanService {
    LoanResponse createLoanForUser(Integer userId, LoanRequest loanRequest);

    List<LoanResponse> getAllLoanForUser(Integer userId);
}
