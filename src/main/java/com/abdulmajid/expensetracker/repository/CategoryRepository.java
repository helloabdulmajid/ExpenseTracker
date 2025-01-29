package com.abdulmajid.expensetracker.repository;

import com.abdulmajid.expensetracker.model.Category;
import com.abdulmajid.expensetracker.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    // Custom query method to check if a category exists by its name
    boolean existsByCategoryName(String name);

    boolean existsByCategoryNameAndUser(String name, User user);

    Category findByCategoryName(String name);

    List<Category> findByUserId(Integer userId);
}
