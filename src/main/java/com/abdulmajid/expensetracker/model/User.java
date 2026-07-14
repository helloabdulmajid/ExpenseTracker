package com.abdulmajid.expensetracker.model;
import com.abdulmajid.expensetracker.model.base.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import java.util.ArrayList;
import java.util.List;

@Entity @Table(name = "users")
@Getter @Setter @NoArgsConstructor
public class User extends BaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotBlank @Size(max = 100) @Column(nullable = false)
    private String name;
    @NotBlank @Size(min = 3, max = 50) @Column(nullable = false, unique = true)
    private String userName;
    @Email @NotBlank @Size(max = 320) @Column(nullable = false, unique = true)
    private String email;
    @NotBlank @Size(min = 8, max = 100) @Column(nullable = false)
    private String password;
    @Size(max = 20)
    private String phone;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Expense> expenses = new ArrayList<>();
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Income> incomes = new ArrayList<>();
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Loan> loans = new ArrayList<>();
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Debt> debts = new ArrayList<>();
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ExpenseCategory> expenseCategories = new ArrayList<>();
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<IncomeCategory> incomeCategories = new ArrayList<>();
    public User(String name, String userName, String email, String password, String phone) {
        this.name = name; this.userName = userName; this.email = email;
        this.password = password; this.phone = phone;
    }
}
