package com.abdulmajid.expensetracker.repository;

import com.abdulmajid.expensetracker.model.IncomeCategory;
import com.abdulmajid.expensetracker.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IncomeCategoryRepository
        extends JpaRepository<IncomeCategory, Integer> {

    boolean existsByCategoryName(
            String categoryName
    );

    boolean existsByCategoryNameAndUser(

            String categoryName,

            User user
    );

    boolean existsByCategoryNameAndDefaultCategory(

            String categoryName,

            boolean defaultCategory
    );

    List<IncomeCategory> findByUserId(
            Integer userId
    );

    List<IncomeCategory> findByDefaultCategoryTrue();
}