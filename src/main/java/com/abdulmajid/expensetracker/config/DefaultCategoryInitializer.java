package com.abdulmajid.expensetracker.config;

import com.abdulmajid.expensetracker.enums.DefaultCategory;
import com.abdulmajid.expensetracker.model.Category;
import com.abdulmajid.expensetracker.repository.CategoryRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DefaultCategoryInitializer {
    @Autowired
    private CategoryRepository categoryRepository;

    @PostConstruct
    public void initDefaultCategories() {
        for (DefaultCategory defaultCategory : DefaultCategory.values()) {
            if (!categoryRepository.existsByCategoryName(defaultCategory.name())) {
                Category category = new Category();
                category.setCategoryName(defaultCategory.name());
                category.setDefaultCategory(true);
                categoryRepository.save(category);
            }
        }
    }
}
