package com.abdulmajid.expensetracker.repository;

import com.abdulmajid.expensetracker.model.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Integer> {
}
