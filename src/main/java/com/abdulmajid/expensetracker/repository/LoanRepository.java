package com.abdulmajid.expensetracker.repository;

import com.abdulmajid.expensetracker.model.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Integer> {
    List<Loan> findByUserId(Integer userId);
}
