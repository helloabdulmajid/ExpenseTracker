package com.abdulmajid.expensetracker.repository;

import com.abdulmajid.expensetracker.model.Debt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DebtRepository extends JpaRepository<Debt, Integer> {
    List<Debt> findByUserId(Integer userId);
}
