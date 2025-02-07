package com.abdulmajid.expensetracker.repository;

import com.abdulmajid.expensetracker.model.ExpenseCategory;
import com.abdulmajid.expensetracker.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExpenseCategoryRepository extends JpaRepository<ExpenseCategory, Integer> {
    // Custom query method to check if a category exists by its name
    boolean existsByCategoryName(String name);

    boolean existsByCategoryNameAndUser(String name, User user);

    boolean existsByCategoryNameAndIsDefaultCategory(String name, boolean isDefault);

    ExpenseCategory findByCategoryName(String name);

    List<ExpenseCategory> findByUserId(Integer userId);
}
