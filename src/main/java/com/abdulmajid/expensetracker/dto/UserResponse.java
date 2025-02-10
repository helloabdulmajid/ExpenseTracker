package com.abdulmajid.expensetracker.dto;

import com.abdulmajid.expensetracker.model.User;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Data
@Builder
@Getter
@Setter
public class UserResponse {
    private Integer id;
    private String name;
    private String userName;
    private String email;
    private String phone;
    private BigDecimal expense;
    private BigDecimal income;
    private BigDecimal debt;
    private BigDecimal loan;
    private Date createdAt;
    private Date updatedAt;

    public UserResponse(Optional<User> existsUser) {

        this.id = existsUser.get().getId();
        this.name = existsUser.get().getName();
        this.userName = existsUser.get().getUserName();
        this.email = existsUser.get().getEmail();
        this.phone = existsUser.get().getPhone();
        this.expense = existsUser.get().getExpense();
        this.income = existsUser.get().getIncome();
        this.debt = existsUser.get().getDebt();
        this.loan = existsUser.get().getLoan();
        this.createdAt = existsUser.get().getCreatedAt();
        this.updatedAt = existsUser.get().getUpdatedAt();


    }

    public UserResponse(List<User> userList) {
    }

    public UserResponse(Integer id, String name, String userName, String phone, String email, Date createdAt, Date updatedAt) {
        this.id = id;
        this.name = name;
        this.userName = userName;
        this.email = email;
        this.phone = phone;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public UserResponse(Integer id, String name, String userName, String email, String phone, BigDecimal expense, BigDecimal income, BigDecimal debt, BigDecimal loan, Date createdAt, Date updatedAt) {
        this.id = id;
        this.name = name;
        this.userName = userName;
        this.email = email;
        this.phone = phone;
        this.expense = expense;
        this.income = income;
        this.debt = debt;
        this.loan = loan;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public BigDecimal getExpense() {
        return expense;
    }

    public void setExpense(BigDecimal expense) {
        this.expense = expense;
    }

    public BigDecimal getIncome() {
        return income;
    }

    public void setIncome(BigDecimal income) {
        this.income = income;
    }

    public BigDecimal getDebt() {
        return debt;
    }

    public void setDebt(BigDecimal debt) {
        this.debt = debt;
    }

    public BigDecimal getLoan() {
        return loan;
    }

    public void setLoan(BigDecimal loan) {
        this.loan = loan;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}
