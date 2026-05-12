package com.abdulmajid.expensetracker.repository;

import com.abdulmajid.expensetracker.model.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ExpenseRepository
        extends JpaRepository<Expense, Integer> {

    List<Expense> findByUserId(
            Integer userId
    );

    Optional<Expense> findByIdAndUserId(

            Integer expenseId,

            Integer userId
    );

    List<Expense> findByUserIdAndDateBetween(

            Integer userId,

            LocalDate firstDay,

            LocalDate lastDay
    );

    List<Expense> findByUserIdAndDate(

            Integer userId,

            LocalDate day
    );
}