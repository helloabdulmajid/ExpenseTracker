package com.abdulmajid.expensetracker.repository;

import com.abdulmajid.expensetracker.model.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;


@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Integer> {
    List<Expense> findByUserId(Integer userId);

    List<Expense> findByUserIdAndDateBetween(Integer userId, LocalDate firstDay, LocalDate lastDay);

    List<Expense> findByUserIdAndDate(Integer userId, LocalDate day);
}
