package com.abdulmajid.expensetracker.dto;

import com.abdulmajid.expensetracker.model.User;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

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
    private Integer expense;
    private Integer income;
    private String debt;
    private Integer loan;
    private Date createdAt ;
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

    public Integer getExpense() {
        return expense;
    }

    public void setExpense(Integer expense) {
        this.expense = expense;
    }

    public Integer getIncome() {
        return income;
    }

    public void setIncome(Integer income) {
        this.income = income;
    }

    public String getDebt() {
        return debt;
    }

    public void setDebt(String debt) {
        this.debt = debt;
    }

    public Integer getLoan() {
        return loan;
    }

    public void setLoan(Integer loan) {
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

    public UserResponse(Integer id, String name, String userName, String phone,String email, Date createdAt, Date updatedAt) {
        this.id = id;
        this.name = name;
        this.userName = userName;
        this.email = email;
        this.phone = phone;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public UserResponse(Integer id, String name, String userName, String email, String phone, Integer expense, Integer income, String debt, Integer loan, Date createdAt, Date updatedAt) {
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
}
