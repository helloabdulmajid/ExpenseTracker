package com.abdulmajid.expensetracker.config;

import com.abdulmajid.expensetracker.enums.DefaultExpenseCategory;
import com.abdulmajid.expensetracker.model.ExpenseCategory;
import com.abdulmajid.expensetracker.repository.ExpenseCategoryRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DefaultExpenseCategoryInitializer {
    @Autowired
    private ExpenseCategoryRepository expenseCategoryRepository;

    @PostConstruct
    public void initDefaultCategories() {
        for (DefaultExpenseCategory defaultExpenseCategory : DefaultExpenseCategory.values()) {
            if (!expenseCategoryRepository.existsByCategoryName(defaultExpenseCategory.name())) {
                ExpenseCategory expenseCategory = new ExpenseCategory();
                expenseCategory.setCategoryName(defaultExpenseCategory.name());
                expenseCategory.setDefaultCategory(true);
                expenseCategoryRepository.save(expenseCategory);
            }
        }
    }
}
