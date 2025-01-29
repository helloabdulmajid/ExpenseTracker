package com.abdulmajid.expensetracker.config;

import com.abdulmajid.expensetracker.enums.DefaultIncomeCategory;
import com.abdulmajid.expensetracker.model.Category;
import com.abdulmajid.expensetracker.repository.CategoryRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DefaultIncomeCategoryInitializer {
    @Autowired
    private CategoryRepository categoryRepository;

    @PostConstruct
    public void initDefaultIncomeCategories() {
        for (DefaultIncomeCategory defaultIncomeCategory : DefaultIncomeCategory.values()) {
            if (!categoryRepository.existsByCategoryName(defaultIncomeCategory.name())) {
                Category category = new Category();
                category.setCategoryName(defaultIncomeCategory.name());
                category.setDefaultCategory(true);
                categoryRepository.save(category);
            }
        }
    }
}
