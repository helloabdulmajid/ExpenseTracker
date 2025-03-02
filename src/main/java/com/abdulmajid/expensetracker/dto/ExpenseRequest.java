package com.abdulmajid.expensetracker.dto;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.util.Date;

public class ExpenseRequest {
    private Integer id;
    @NotNull(message = "Amount cannot be Empty")
    @Min(value = 1, message = "Expense Amount should be greater than 0")
    @Max(value = 1000000, message = "Expense Amount should be Lower than 1M")
    private BigDecimal amount;

    @NotBlank(message = "Please choose a payment mode")
    @Pattern(regexp = "CASH|CARD|UPI", message = "Invalid payment mode. Choose CASH, CARD, or UPI")
    private String paymentMode;
    private String note;
    @NotBlank(message = "Day cannot be empty")
    private String day;
    @NotBlank(message = "Date cannot be empty")
    private String date;
    private Date createdAt;
    private Date updatedAt;
    @NotNull(message = "Category ID is required")
    @Min(value = 1, message = "Category ID must be a positive number")
    private Integer categoryId;

    @NotNull(message = "User ID is required")
    @Min(value = 1, message = "User ID must be a positive number")
    private Integer userId;

    public ExpenseRequest() {
    }

    public ExpenseRequest(Integer id, BigDecimal amount, String paymentMode, String note, String day,
                          String date, Date createdAt, Date updatedAt, Integer categoryId,
                          Integer userId) {
        this.id = id;
        this.amount = amount;
        this.paymentMode = paymentMode;
        this.note = note;
        this.day = day;
        this.date = date;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.categoryId = categoryId;
        this.userId = userId;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
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

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}