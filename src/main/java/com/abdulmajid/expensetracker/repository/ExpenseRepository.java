package com.abdulmajid.expensetracker.repository;

import com.abdulmajid.expensetracker.enums.PaymentMode;
import com.abdulmajid.expensetracker.model.Expense;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ExpenseRepository
        extends JpaRepository<Expense, Integer> {

    List<Expense> findByUserId(
            Integer userId
    );

    Page<Expense> findByUserId(
            Integer userId,
            Pageable pageable
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

    Page<Expense>
    findByUserIdAndNoteContainingIgnoreCase(

            Integer userId,

            String keyword,

            Pageable pageable
    );

    Page<Expense>
    findByUserIdAndPaymentMode(

            Integer userId,

            PaymentMode paymentMode,

            Pageable pageable
    );

    Page<Expense>
    findByUserIdAndPaymentModeAndNoteContainingIgnoreCase(

            Integer userId,

            PaymentMode paymentMode,

            String keyword,

            Pageable pageable
    );

    @Query("""
            
                    SELECT COALESCE(
                        SUM(e.amount),
                        0
                    )
            
                    FROM Expense e
            
                    WHERE e.user.id = :userId
            
            """)
    BigDecimal getTotalExpensesByUserId(
            Integer userId
    );
}