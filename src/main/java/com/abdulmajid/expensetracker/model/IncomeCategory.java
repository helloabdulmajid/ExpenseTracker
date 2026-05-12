package com.abdulmajid.expensetracker.model;

import com.abdulmajid.expensetracker.model.base.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "income_categories")

@Getter
@Setter
@NoArgsConstructor

public class IncomeCategory extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Category name is required")
    @Column(nullable = false)
    private String categoryName;

    @Column(nullable = false)
    private boolean defaultCategory;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public IncomeCategory(
            String categoryName,
            boolean defaultCategory,
            User user
    ) {
        this.categoryName = categoryName;
        this.defaultCategory = defaultCategory;
        this.user = user;
    }
}