package com.abdulmajid.expensetracker.model;
import com.abdulmajid.expensetracker.model.base.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity @Table(name = "expense_categories", indexes = {
    @Index(name = "idx_exp_cat_user_id", columnList = "user_id")
}, uniqueConstraints = {
    @UniqueConstraint(name = "uk_exp_cat_user_name", columnNames = {"user_id", "category_name"})
})
@Getter @Setter @NoArgsConstructor
public class ExpenseCategory extends BaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotBlank @Size(max = 50) @Column(nullable = false)
    private String categoryName;
    @Column(nullable = false)
    private boolean defaultCategory;
    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "user_id")
    private User user;
    public ExpenseCategory(String categoryName, boolean defaultCategory, User user) {
        this.categoryName = categoryName; this.defaultCategory = defaultCategory; this.user = user;
    }
}
