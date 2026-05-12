package com.abdulmajid.expensetracker.repository;

import com.abdulmajid.expensetracker.model.Income;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IncomeRepository
        extends JpaRepository<Income, Integer> {

    List<Income> findByUserId(Integer userId);

    Optional<Income> findByIdAndUserId(
            Integer incomeId,
            Integer userId
    );

}