package com.abdulmajid.expensetracker.service.impl;

import com.abdulmajid.expensetracker.dto.CategoryRequest;
import com.abdulmajid.expensetracker.exception.custom.CategoryNotFoundException;
import com.abdulmajid.expensetracker.exception.custom.UserNotFoundException;
import com.abdulmajid.expensetracker.model.Category;
import com.abdulmajid.expensetracker.model.User;
import com.abdulmajid.expensetracker.repository.CategoryRepository;
import com.abdulmajid.expensetracker.repository.UserRepository;
import com.abdulmajid.expensetracker.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> getCategoriesForUser(Integer userId) {
        User existsUser = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + userId));

        return categoryRepository.findByUserId(userId);
    }

    @Override
    public void createCategoryForUser(Integer userId, CategoryRequest categoryRequest) {
        // Check if user exists
        User existsUser = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + userId));

        // Check if the category already exists for the user
        if (categoryRepository.existsByCategoryName(categoryRequest.getCategoryName()) ||
                categoryRepository.existsByCategoryNameAndUser(categoryRequest.getCategoryName(), existsUser)) {
            throw new CategoryNotFoundException("Category already exists for this user");
        }

        // Create new category for the user
        Category category = new Category();
        category.setCategoryName(categoryRequest.getCategoryName().toUpperCase());
        category.setDefaultCategory(false);
        category.setUser(existsUser);

        categoryRepository.save(category);
    }

    // Not working now, using eager loading or using dto for returning dto class not entity, relationship makes problem
    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

}
