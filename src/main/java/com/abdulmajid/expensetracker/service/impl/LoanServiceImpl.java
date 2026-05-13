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

    
    // CREATE LOAN
    @Override
    public LoanResponse createLoanForUser(

            Integer userId,

            LoanRequest loanRequest
    ) {

        User user = getUserById(userId);

        Loan loan = new Loan(

                loanRequest.getAmount(),

                loanRequest.getLender(),

                loanRequest.getLenderName(),

                loanRequest.getBorrower(),

                loanRequest.getBorrowerName(),

                loanRequest.getDueDate(),

                loanRequest.getStatus(),

                loanRequest.getLoanType(),

                loanRequest.getRecurring(),

                loanRequest.getNote(),

                loanRequest.getDate(),

                user
        );

        Loan savedLoan =
                loanRepository.save(loan);

        return mapToResponse(savedLoan);
    }

    // GET ALL USER LOANS
    @Override
    public List<LoanResponse> getAllLoanForUser(
            Integer userId
    ) {

        getUserById(userId);

        return loanRepository.findByUserId(userId)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    // GET SINGLE LOAN
    @Override
    public LoanResponse getOneLoan(

            Integer userId,

            Integer loanId
    ) {

        Loan loan =
                getLoanByIdAndUserId(
                        loanId,
                        userId
                );

        return mapToResponse(loan);
    }

    // GET ALL LOANS
    @Override
    public List<LoanResponse> getAllLoans() {

        return loanRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    // UPDATE LOAN
    @Override
    public LoanResponse updateLoan(

            Integer userId,

            Integer loanId,

            LoanRequest loanRequest
    ) {

        Loan loan =
                getLoanByIdAndUserId(
                        loanId,
                        userId
                );

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

    // DELETE LOAN
    @Override
    public void deleteLoan(

            Integer userId,

            Integer loanId
    ) {

        Loan loan =
                getLoanByIdAndUserId(
                        loanId,
                        userId
                );

        loanRepository.delete(loan);
    }

    // HELPER METHOD
    private User getUserById(
            Integer userId
    ) {

        return userRepository.findById(userId)
                .orElseThrow(() ->
                        new UserNotFoundException(
                                "User not found with ID: "
                                        + userId
                        )
                );
    }

    // HELPER METHOD
    private Loan getLoanByIdAndUserId(

            Integer loanId,

            Integer userId
    ) {

        return loanRepository.findByIdAndUserId(
                        loanId,
                        userId
                )
                .orElseThrow(() ->
                        new LoanNotFoundException(
                                "Loan not found with ID: "
                                        + loanId
                        )
                );
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