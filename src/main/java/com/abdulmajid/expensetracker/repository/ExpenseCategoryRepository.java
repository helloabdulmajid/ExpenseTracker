package com.abdulmajid.expensetracker.repository;

import com.abdulmajid.expensetracker.model.ExpenseCategory;
import com.abdulmajid.expensetracker.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExpenseCategoryRepository
        extends JpaRepository<ExpenseCategory, Integer> {

    boolean existsByCategoryName(String categoryName);

    boolean existsByCategoryNameAndUser(
            String categoryName,
            User user
    );

    boolean existsByCategoryNameAndDefaultCategory(

            String categoryName,

            boolean defaultCategory
    );

    List<ExpenseCategory> findByUserId(
            Integer userId
    );

    List<ExpenseCategory> findByDefaultCategoryTrue();
}