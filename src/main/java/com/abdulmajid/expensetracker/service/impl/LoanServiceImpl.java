package com.abdulmajid.expensetracker.service.impl;

import com.abdulmajid.expensetracker.dto.LoanRequest;
import com.abdulmajid.expensetracker.dto.LoanResponse;
import com.abdulmajid.expensetracker.exception.custom.UserNotFoundException;
import com.abdulmajid.expensetracker.model.Loan;
import com.abdulmajid.expensetracker.model.User;
import com.abdulmajid.expensetracker.repository.LoanRepository;
import com.abdulmajid.expensetracker.repository.UserRepository;
import com.abdulmajid.expensetracker.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LoanServiceImpl implements LoanService {

    @Autowired
    private LoanRepository loanRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public LoanResponse createLoanForUser(Integer userId, LoanRequest loanRequest) {
        // get user from userId(param)
        User existsUser = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + userId));

        Loan loan = new Loan(loanRequest.getAmount(), loanRequest.getLender(), loanRequest.getBorrower(),
                loanRequest.getInterestRate(), loanRequest.getLoanType(), loanRequest.getTenureMonths(),
                loanRequest.getStartDate(), loanRequest.getDueDate(), loanRequest.getPaymentMode(),
                loanRequest.getIsPaid(), loanRequest.getRemainingBalance(), loanRequest.getStatus(),
                loanRequest.getNote(), loanRequest.getDay(), loanRequest.getDate(), existsUser);
        Loan saveLoan = loanRepository.save(loan);

        return new LoanResponse(saveLoan.getId(), saveLoan.getAmount(), saveLoan.getLender(),
                saveLoan.getBorrower(), saveLoan.getInterestRate(), saveLoan.getLoanType(),
                saveLoan.getTenureMonths(), saveLoan.getStartDate(), saveLoan.getDueDate(),
                saveLoan.getPaymentMode(), saveLoan.getIsPaid(), saveLoan.getRemainingBalance(),
                saveLoan.getStatus(), saveLoan.getNote(), saveLoan.getDay(), saveLoan.getDate(),
                saveLoan.getCreatedAt(), saveLoan.getUpdatedAt(), saveLoan.getUser());
    }

    @Override
    public List<LoanResponse> getAllLoanForUser(Integer userId) {
        // get user from userId
        User existsUser = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + userId));

        List<Loan> listLoan = loanRepository.findByUserId(userId);
        ArrayList<LoanResponse> responseList = new ArrayList<>();

        for (Loan loan : listLoan) {
            LoanResponse response = new LoanResponse(loan.getId(), loan.getAmount(), loan.getLender(),
                    loan.getBorrower(), loan.getInterestRate(), loan.getLoanType(),
                    loan.getTenureMonths(), loan.getStartDate(), loan.getDueDate(),
                    loan.getPaymentMode(), loan.getIsPaid(), loan.getRemainingBalance(),
                    loan.getStatus(), loan.getNote(), loan.getDay(), loan.getDate(),
                    loan.getCreatedAt(), loan.getUpdatedAt(), loan.getUser());

            responseList.add(response);

        }
        return responseList;

    }
}
