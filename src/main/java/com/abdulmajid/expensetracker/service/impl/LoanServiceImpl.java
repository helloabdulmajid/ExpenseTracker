package com.abdulmajid.expensetracker.service.impl;

import com.abdulmajid.expensetracker.dto.LoanRequest;
import com.abdulmajid.expensetracker.dto.LoanResponse;
import com.abdulmajid.expensetracker.exception.custom.LoanNotFoundException;
import com.abdulmajid.expensetracker.exception.custom.UserNotFoundException;
import com.abdulmajid.expensetracker.model.Loan;
import com.abdulmajid.expensetracker.model.User;
import com.abdulmajid.expensetracker.repository.LoanRepository;
import com.abdulmajid.expensetracker.repository.UserRepository;
import com.abdulmajid.expensetracker.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
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

        //get Total income from user table
        BigDecimal oldTotalLoan = existsUser.getLoan();
        BigDecimal newTotalLoan = oldTotalLoan.add(loanRequest.getAmount());

        existsUser.setLoan(newTotalLoan);
        existsUser.setUpdatedAt(new Date());
        userRepository.save(existsUser);

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
                    loan.getCreatedAt(), loan.getUpdatedAt());

            responseList.add(response);

        }
        return responseList;

    }

    @Override
    public LoanResponse getOneLoan(Integer loanId) {

        Loan loan = loanRepository.findById(loanId)
                .orElseThrow(() -> new UserNotFoundException("Loan not found with ID: " + loanId));

        return new LoanResponse(loan.getId(), loan.getAmount(), loan.getLender(),
                loan.getBorrower(), loan.getInterestRate(), loan.getLoanType(),
                loan.getTenureMonths(), loan.getStartDate(), loan.getDueDate(),
                loan.getPaymentMode(), loan.getIsPaid(), loan.getRemainingBalance(),
                loan.getStatus(), loan.getNote(), loan.getDay(), loan.getDate(),
                loan.getCreatedAt(), loan.getUpdatedAt(), loan.getUser());
    }

    @Override
    public List<LoanResponse> getAllLoans() {
        List<Loan> allLoans = loanRepository.findAll();
        ArrayList<LoanResponse> loanResponses = new ArrayList<>();
        for (Loan loan : allLoans) {
            LoanResponse loanResponse = new LoanResponse(
                    loan.getId(), loan.getAmount(), loan.getLender(),
                    loan.getBorrower(), loan.getInterestRate(), loan.getLoanType(),
                    loan.getTenureMonths(), loan.getStartDate(), loan.getDueDate(),
                    loan.getPaymentMode(), loan.getIsPaid(), loan.getRemainingBalance(),
                    loan.getStatus(), loan.getNote(), loan.getDay(), loan.getDate(),
                    loan.getCreatedAt(), loan.getUpdatedAt()
            );
            loanResponses.add(loanResponse);

            // if we need user details also then use this one
//            LoanResponse loanResponse = new LoanResponse(
//                    loan.getId(), loan.getAmount(), loan.getLender(),
//                    loan.getBorrower(), loan.getInterestRate(), loan.getLoanType(),
//                    loan.getTenureMonths(), loan.getStartDate(), loan.getDueDate(),
//                    loan.getPaymentMode(), loan.getIsPaid(), loan.getRemainingBalance(),
//                    loan.getStatus(), loan.getNote(), loan.getDay(), loan.getDate(),
//                    loan.getCreatedAt(), loan.getUpdatedAt(),loan.getUser()
//            );
//            loanResponses.add(loanResponse);


        }
        return loanResponses;
    }

    @Override
    public String updateLoan(Integer userId, Integer loanId, LoanRequest loanRequest) {
        // get user from userId(param)
        User existsUser = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + userId));

        //get Loan from LoanId
        Loan existsLoan = loanRepository.findById(loanId)
                .orElseThrow(() -> new LoanNotFoundException("Loan Not found with Loan Id:  " + loanId));

        BigDecimal oldAmount = existsLoan.getAmount();
        BigDecimal newAmount = loanRequest.getAmount();

        // If new amount is the same as old, just update the Loan all records and return
        if (newAmount.compareTo(oldAmount) == 0) {
            existsLoan.setAmount(loanRequest.getAmount());
            existsLoan.setLender(loanRequest.getLender());
            existsLoan.setBorrower(loanRequest.getBorrower());
            existsLoan.setInterestRate(loanRequest.getInterestRate());
            existsLoan.setLoanType(loanRequest.getLoanType());
            existsLoan.setTenureMonths(loanRequest.getTenureMonths());
            existsLoan.setStartDate(loanRequest.getStartDate());
            existsLoan.setStartDate(loanRequest.getStartDate());
            existsLoan.setDueDate(loanRequest.getDueDate());
            existsLoan.setPaymentMode(loanRequest.getPaymentMode());
            existsLoan.setIsPaid(loanRequest.getIsPaid());
            existsLoan.setRemainingBalance(loanRequest.getRemainingBalance());
            existsLoan.setStatus(loanRequest.getStatus());
            existsLoan.setNote(loanRequest.getNote());
            existsLoan.setDay(existsLoan.getDay());
            existsLoan.setDate(existsLoan.getDate());
            existsLoan.setUpdatedAt(new Date());
            loanRepository.save(existsLoan);
            return "Loan Updated SuccessFully , Loan Id :" + loanId; //  Exit the function early (no need to update user's Loan)
        }

        // if NewAmount is greater than old Amount → Add the difference to user table

        if (newAmount.compareTo(oldAmount) > 0) {
            BigDecimal difference = newAmount.subtract(oldAmount);
            existsUser.setLoan(existsUser.getLoan().add(difference));
        } else {
            // if New amount is smaller → Subtract the difference
            BigDecimal difference = oldAmount.subtract(newAmount);

            if (existsUser.getLoan().subtract(difference).compareTo(BigDecimal.ZERO) < 0) {
                throw new LoanNotFoundException("Not enough balance to update this Loan");
            }

            existsUser.setLoan(existsUser.getLoan().subtract(difference));
        }

        // Update the Loan record
        existsLoan.setAmount(newAmount);
        existsLoan.setLender(loanRequest.getLender());
        existsLoan.setBorrower(loanRequest.getBorrower());
        existsLoan.setInterestRate(loanRequest.getInterestRate());
        existsLoan.setLoanType(loanRequest.getLoanType());
        existsLoan.setTenureMonths(loanRequest.getTenureMonths());
        existsLoan.setStartDate(loanRequest.getStartDate());
        existsLoan.setStartDate(loanRequest.getStartDate());
        existsLoan.setDueDate(loanRequest.getDueDate());
        existsLoan.setPaymentMode(loanRequest.getPaymentMode());
        existsLoan.setIsPaid(loanRequest.getIsPaid());
        existsLoan.setRemainingBalance(loanRequest.getRemainingBalance());
        existsLoan.setStatus(loanRequest.getStatus());
        existsLoan.setNote(loanRequest.getNote());
        existsLoan.setDay(existsLoan.getDay());
        existsLoan.setDate(existsLoan.getDate());
        existsLoan.setUpdatedAt(new Date());

        // Save updates
        loanRepository.save(existsLoan);
        existsUser.setUpdatedAt(new Date());
        userRepository.save(existsUser);

        return "Loan Updated SuccessFully , Loan Id :" + loanId;
    }

    @Override
    public String deleteLoan(Integer userId, Integer loanId) {
        User existsUser = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + userId));

        Loan existsLoan = loanRepository.findById(loanId)
                .orElseThrow(() -> new LoanNotFoundException("Loan Not found with Loan Id:  " + loanId));

        BigDecimal LoanAmount = existsLoan.getAmount();

        try {
            existsUser.setLoan(existsUser.getLoan().subtract(LoanAmount));

            // Remove Loan record
            loanRepository.delete(existsLoan);
            existsUser.setUpdatedAt(new Date());
            userRepository.save(existsUser);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Deleted Loan Id : -> " + loanId;
    }
}
