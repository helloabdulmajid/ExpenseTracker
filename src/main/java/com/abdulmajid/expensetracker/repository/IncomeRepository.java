package com.abdulmajid.expensetracker.repository;

import com.abdulmajid.expensetracker.model.Income;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IncomeRepository extends JpaRepository<Income, Integer> {
    // List<String> findByUserId(Integer userId);
    List<Income> findByUserId(Integer userId);

}
