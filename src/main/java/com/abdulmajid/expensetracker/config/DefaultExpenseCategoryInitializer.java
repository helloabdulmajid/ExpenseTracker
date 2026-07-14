package com.abdulmajid.expensetracker.config;

import com.abdulmajid.expensetracker.enums.DefaultExpenseCategory;
import com.abdulmajid.expensetracker.model.ExpenseCategory;
import com.abdulmajid.expensetracker.repository.ExpenseCategoryRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor

public class DefaultExpenseCategoryInitializer {

    private final ExpenseCategoryRepository expenseCategoryRepository;

    @PostConstruct
    public void initDefaultCategories() {

        for (DefaultExpenseCategory category
                : DefaultExpenseCategory.values()) {

            boolean exists =
                    expenseCategoryRepository
                            .existsByCategoryName(
                                    category.name()
                            );

            if (!exists) {

                ExpenseCategory expenseCategory =
                        new ExpenseCategory();

                expenseCategory.setCategoryName(
                        category.name()
                );

                expenseCategory.setDefaultCategory(true);

                expenseCategoryRepository.save(expenseCategory);
            }
        }
    }
}