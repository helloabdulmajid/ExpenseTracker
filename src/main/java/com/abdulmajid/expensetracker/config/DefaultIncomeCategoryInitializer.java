package com.abdulmajid.expensetracker.config;

import com.abdulmajid.expensetracker.enums.DefaultIncomeCategory;
import com.abdulmajid.expensetracker.model.IncomeCategory;
import com.abdulmajid.expensetracker.repository.IncomeCategoryRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DefaultIncomeCategoryInitializer {

    @Autowired
    private IncomeCategoryRepository incomeCategoryRepository;

    @PostConstruct
    public void initDefaultIncomeCategories() {
        for (DefaultIncomeCategory defaultIncomeCategory : DefaultIncomeCategory.values()) {
            if (!incomeCategoryRepository.existsByCategoryName(defaultIncomeCategory.name())) {
                IncomeCategory incomeCategory = new IncomeCategory();
                incomeCategory.setCategoryName(defaultIncomeCategory.name());
                incomeCategory.setDefaultCategory(true);
                incomeCategoryRepository.save(incomeCategory);
            }
        }
    }
}
