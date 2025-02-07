package com.abdulmajid.expensetracker.repository;

import com.abdulmajid.expensetracker.model.Income;
import com.abdulmajid.expensetracker.model.IncomeCategory;
import com.abdulmajid.expensetracker.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IncomeCategoryRepository extends JpaRepository<IncomeCategory, Integer> {
    // Custom query method to check if a category exists by its name
    boolean existsByCategoryName(String name);

    boolean existsByCategoryNameAndUser(String name, User user);

    Income findByCategoryName(String name);

    boolean existsByCategoryNameAndIsDefaultCategory(String name, boolean isDefault);

    List<IncomeCategory> findByUserId(Integer userId);

}
