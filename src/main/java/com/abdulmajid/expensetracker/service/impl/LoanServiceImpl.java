package com.abdulmajid.expensetracker.service.impl;

import com.abdulmajid.expensetracker.dto.request.LoanRequest;
import com.abdulmajid.expensetracker.dto.response.LoanResponse;
import com.abdulmajid.expensetracker.exception.custom.InvalidArgumentException;
import com.abdulmajid.expensetracker.exception.custom.LoanNotFoundException;
import com.abdulmajid.expensetracker.exception.custom.UserNotFoundException;
import com.abdulmajid.expensetracker.model.Loan;
import com.abdulmajid.expensetracker.model.User;
import com.abdulmajid.expensetracker.repository.LoanRepository;
import com.abdulmajid.expensetracker.repository.UserRepository;
import com.abdulmajid.expensetracker.security.utils.SecurityUtils;
import com.abdulmajid.expensetracker.service.LoanService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor

public class LoanServiceImpl
        implements LoanService {

    private final LoanRepository loanRepository;

    private final UserRepository userRepository;

    // NEW CREATE LOAN

    @Override
    public LoanResponse createLoan(
            LoanRequest loanRequest
    ) {

        String email =
                SecurityUtils.getCurrentUserEmail();

        User user = userRepository
                .findByEmail(email)
                .orElseThrow(() ->
                        new UserNotFoundException(
                                "User not found"
                        )
                );

        Loan loan = new Loan();

        loan.setAmount(
                loanRequest.getAmount()
        );

        loan.setLender(
                loanRequest.getLender()
        );

        loan.setLenderName(
                loanRequest.getLenderName()
        );

        loan.setBorrower(
                loanRequest.getBorrower()
        );

        loan.setBorrowerName(
                loanRequest.getBorrowerName()
        );

        loan.setDueDate(
                loanRequest.getDueDate()
        );

        loan.setStatus(
                loanRequest.getStatus()
        );

        loan.setLoanType(
                loanRequest.getLoanType()
        );

        loan.setRecurring(
                loanRequest.getRecurring()
        );

        loan.setNote(
                loanRequest.getNote()
        );

        loan.setDate(
                loanRequest.getDate()
        );

        // IMPORTANT 🔥
        loan.setUser(user);

        Loan savedLoan =
                loanRepository.save(loan);

        return mapToResponse(savedLoan);
    }

    //GET ALL CURRENT USER LOANS

    @Override
    public List<LoanResponse>
    getCurrentUserLoans() {

        String email =
                SecurityUtils.getCurrentUserEmail();

        User user = userRepository
                .findByEmail(email)
                .orElseThrow(() ->
                        new UserNotFoundException(
                                "User not found"
                        )
                );

        return loanRepository
                .findByUserId(user.getId())
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    // GET SINGLE CURRENT USER LOAN

    @Override
    public LoanResponse getCurrentUserLoan(
            Integer loanId
    ) {

        String email =
                SecurityUtils.getCurrentUserEmail();

        User user = userRepository
                .findByEmail(email)
                .orElseThrow(() ->
                        new UserNotFoundException(
                                "User not found"
                        )
                );

        Loan loan = loanRepository
                .findById(loanId)
                .orElseThrow(() ->
                        new LoanNotFoundException(
                                "Loan not found"
                        )
                );

        if (!loan.getUser()
                .getId()
                .equals(user.getId())) {

            throw new InvalidArgumentException(
                    "You are not authorized to access this loan"
            );
        }

        return mapToResponse(loan);
    }

    //NEW UPDATE LOAN

    @Override
    public LoanResponse updateLoan(

            Integer loanId,

            LoanRequest loanRequest
    ) {

        String email =
                SecurityUtils.getCurrentUserEmail();

        User user = userRepository
                .findByEmail(email)
                .orElseThrow(() ->
                        new UserNotFoundException(
                                "User not found"
                        )
                );

        Loan loan = loanRepository
                .findById(loanId)
                .orElseThrow(() ->
                        new LoanNotFoundException(
                                "Loan not found"
                        )
                );

        if (!loan.getUser()
                .getId()
                .equals(user.getId())) {

            throw new InvalidArgumentException(
                    "You are not authorized to update this loan"
            );
        }

        loan.setAmount(
                loanRequest.getAmount()
        );

        loan.setLender(
                loanRequest.getLender()
        );

        loan.setLenderName(
                loanRequest.getLenderName()
        );

        loan.setBorrower(
                loanRequest.getBorrower()
        );

        loan.setBorrowerName(
                loanRequest.getBorrowerName()
        );

        loan.setDueDate(
                loanRequest.getDueDate()
        );

        loan.setStatus(
                loanRequest.getStatus()
        );

        loan.setLoanType(
                loanRequest.getLoanType()
        );

        loan.setRecurring(
                loanRequest.getRecurring()
        );

        loan.setNote(
                loanRequest.getNote()
        );

        loan.setDate(
                loanRequest.getDate()
        );

        Loan updatedLoan =
                loanRepository.save(loan);

        return mapToResponse(updatedLoan);
    }

    //NEW DELETE LOAN

    @Override
    public LoanResponse deleteLoan(
            Integer loanId
    ) {

        String email =
                SecurityUtils.getCurrentUserEmail();

        User user = userRepository
                .findByEmail(email)
                .orElseThrow(() ->
                        new UserNotFoundException(
                                "User not found"
                        )
                );

        Loan loan = loanRepository
                .findById(loanId)
                .orElseThrow(() ->
                        new LoanNotFoundException(
                                "Loan not found"
                        )
                );

        if (!loan.getUser()
                .getId()
                .equals(user.getId())) {

            throw new InvalidArgumentException(
                    "You are not authorized to delete this loan"
            );
        }

        LoanResponse response =
                mapToResponse(loan);

        loanRepository.delete(loan);

        return response;
    }

    
    // MAPPER METHOD
    private LoanResponse mapToResponse(
            Loan loan
    ) {

        LoanResponse response =
                new LoanResponse();

        response.setId(
                loan.getId()
        );

        response.setAmount(
                loan.getAmount()
        );

        response.setLender(
                loan.getLender()
        );

        response.setLenderName(
                loan.getLenderName()
        );

        response.setBorrower(
                loan.getBorrower()
        );

        response.setBorrowerName(
                loan.getBorrowerName()
        );

        response.setDueDate(
                loan.getDueDate()
        );

        response.setStatus(
                loan.getStatus()
        );

        response.setLoanType(
                loan.getLoanType()
        );

        response.setRecurring(
                loan.getRecurring()
        );

        response.setNote(
                loan.getNote()
        );

        response.setDate(
                loan.getDate()
        );

        response.setUserId(
                loan.getUser().getId()
        );

        response.setCreatedAt(
                loan.getCreatedAt()
        );

        response.setUpdatedAt(
                loan.getUpdatedAt()
        );

        return response;
    }
}