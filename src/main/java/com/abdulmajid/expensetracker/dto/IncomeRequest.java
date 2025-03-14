package com.abdulmajid.expensetracker.dto;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

public class IncomeRequest {
    private Integer id;
    @NotNull(message = "Amount cannot be null")
    @DecimalMin(value = "0.01", message = "Amount must be greater than 0") // Prevents negative and zero values
    private BigDecimal amount;
    @NotBlank(message = "Receive mode is required")
    private String receiveMode;
    private String note;
    @NotBlank(message = "Day cannot be empty")
    private String day;
    @PastOrPresent(message = "Date cannot be in the future")
    @NotNull(message = "Date cannot be null")
    private LocalDate date;
    private Date createdAt;
    private Date updatedAt;

    @NotNull(message = "Source ID is required")
    @Min(value = 1, message = "Category ID must be a positive number")
    private Integer categoryId;

    private Integer userId;

    public IncomeRequest() {
    }

    public IncomeRequest(Integer id, BigDecimal amount, Integer categoryId, String receiveMode,
                         String note, String day, LocalDate date, Date createdAt,
                         Date updatedAt, Integer userId) {
        this.id = id;
        this.amount = amount;
        this.categoryId = categoryId;
        this.receiveMode = receiveMode;
        this.note = note;
        this.day = day;
        this.date = date;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
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

    public String getReceiveMode() {
        return receiveMode;
    }

    public void setReceiveMode(String receiveMode) {
        this.receiveMode = receiveMode;
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
