package com.abdulmajid.expensetracker.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

@Entity
@Table(name = "income_categories")
public class IncomeCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String categoryName;

    @Column(nullable = false)
    private boolean isDefaultCategory;
    @JsonManagedReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = true) // User can be null for default categories
    private User user;

    public IncomeCategory() {
    }

    public IncomeCategory(Integer id, String categoryName, boolean isDefaultCategory, User user) {
        this.id = id;
        this.categoryName = categoryName;
        this.isDefaultCategory = isDefaultCategory;
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public boolean isDefaultCategory() {
        return isDefaultCategory;
    }

    public void setDefaultCategory(boolean defaultCategory) {
        isDefaultCategory = defaultCategory;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
