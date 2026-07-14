package com.abdulmajid.expensetracker.repository;

import com.abdulmajid.expensetracker.model.Income;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface IncomeRepository
        extends JpaRepository<Income, Integer> {

    List<Income> findByUserId(Integer userId);

    Optional<Income> findByIdAndUserId(
            Integer incomeId,
            Integer userId
    );

    @Query("""
            SELECT COALESCE(
                SUM(i.amount),
                0
            )
            FROM Income i
            WHERE i.user.id = :userId
            """)
    BigDecimal getTotalIncomeByUserId(
            @Param("userId") Integer userId
    );

}