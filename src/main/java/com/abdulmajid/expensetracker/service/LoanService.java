package com.abdulmajid.expensetracker.service;

import com.abdulmajid.expensetracker.dto.request.LoanRequest;
import com.abdulmajid.expensetracker.dto.response.LoanResponse;

import java.util.List;

public interface LoanService {

    LoanResponse createLoanForUser(

            Integer userId,

            LoanRequest loanRequest
    );

    List<LoanResponse> getAllLoanForUser(
            Integer userId
    );

    LoanResponse getOneLoan(

            Integer userId,

            Integer loanId
    );

    List<LoanResponse> getAllLoans();

    LoanResponse updateLoan(

            Integer userId,

            Integer loanId,

            LoanRequest loanRequest
    );

    void deleteLoan(

            Integer userId,

            Integer loanId
    );
}