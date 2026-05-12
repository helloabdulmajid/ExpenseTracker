package com.abdulmajid.expensetracker.config;

import com.abdulmajid.expensetracker.enums.DefaultIncomeCategory;
import com.abdulmajid.expensetracker.model.IncomeCategory;
import com.abdulmajid.expensetracker.repository.IncomeCategoryRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor

public class DefaultIncomeCategoryInitializer {

    private final IncomeCategoryRepository incomeCategoryRepository;

    @PostConstruct
    public void initDefaultIncomeCategories() {

        for (DefaultIncomeCategory category
                : DefaultIncomeCategory.values()) {

            boolean exists =
                    incomeCategoryRepository
                            .existsByCategoryName(
                                    category.name()
                            );

            if (!exists) {

                IncomeCategory incomeCategory =
                        new IncomeCategory();

                incomeCategory.setCategoryName(
                        category.name()
                );

                incomeCategory.setDefaultCategory(true);

                incomeCategoryRepository.save(incomeCategory);
            }
        }
    }
}