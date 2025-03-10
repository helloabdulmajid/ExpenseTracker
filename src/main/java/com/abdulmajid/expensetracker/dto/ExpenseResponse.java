package com.abdulmajid.expensetracker.dto;

import com.abdulmajid.expensetracker.model.User;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

public class ExpenseResponse {
    private Integer id;
    private BigDecimal amount;
    private String paymentMode;
    private String note;
    private String day;
    private LocalDate date;
    private Date createdAt;
    private Date updatedAt;

    private ExpenseCategoryResponse category;
    private User user;

    public ExpenseResponse() {
    }

    public ExpenseResponse(Integer id, BigDecimal amount, String paymentMode, String note,
                           String day, LocalDate date, Date createdAt, Date updatedAt,
                           ExpenseCategoryResponse category, User user) {
        this.id = id;
        this.amount = amount;
        this.paymentMode = paymentMode;
        this.note = note;
        this.day = day;
        this.date = date;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.category = category;
        this.user = user;
    }

    public ExpenseResponse(Integer id, BigDecimal amount, String paymentMode, String note,
                           String day, LocalDate date, Date createdAt, Date updatedAt,
                           ExpenseCategoryResponse category) {
        this.id = id;
        this.amount = amount;
        this.paymentMode = paymentMode;
        this.note = note;
        this.day = day;
        this.date = date;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.category = category;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(String paymentMode) {
        this.paymentMode = paymentMode;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
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

    public ExpenseCategoryResponse getCategory() {
        return category;
    }

    public void setCategory(ExpenseCategoryResponse category) {
        this.category = category;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
