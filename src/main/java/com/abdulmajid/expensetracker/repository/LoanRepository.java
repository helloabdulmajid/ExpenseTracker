package com.abdulmajid.expensetracker.repository;

import com.abdulmajid.expensetracker.model.Loan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LoanRepository
        extends JpaRepository<Loan, Integer> {

    List<Loan> findByUserId(
            Integer userId
    );

    Optional<Loan> findByIdAndUserId(

            Integer loanId,

            Integer userId
    );
}