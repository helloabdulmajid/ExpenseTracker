package com.abdulmajid.expensetracker.dto;

import com.abdulmajid.expensetracker.model.User;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

public class IncomeResponse {
    private Integer id;
    private BigDecimal amount;
    // private String source;
    private String receiveMode;
    private String note;
    private String day;
    private LocalDate date;
    private Date createdAt;
    private Date updatedAt;
    private User user;
    private IncomeCategoryResponse incomeCategoryResponse;

    public IncomeResponse() {
    }

    public IncomeResponse(Integer id, BigDecimal amount, String receiveMode,
                          String note, String day, LocalDate date, Date createdAt, Date updatedAt,
                          IncomeCategoryResponse incomeCategoryResponse, User user) {
        this.id = id;
        this.amount = amount;
        this.receiveMode = receiveMode;
        this.note = note;
        this.day = day;
        this.date = date;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.incomeCategoryResponse = incomeCategoryResponse;
        this.user = user;
    }

    public IncomeResponse(Integer id, BigDecimal amount, String receiveMode,
                          String note, String day, LocalDate date, Date createdAt, Date updatedAt,
                          IncomeCategoryResponse incomeCategoryResponse) {
        this.id = id;
        this.amount = amount;
        this.receiveMode = receiveMode;
        this.note = note;
        this.day = day;
        this.date = date;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.incomeCategoryResponse = incomeCategoryResponse;

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public IncomeCategoryResponse getIncomeCategoryResponse() {
        return incomeCategoryResponse;
    }

    public void setIncomeCategoryResponse(IncomeCategoryResponse incomeCategoryResponse) {
        this.incomeCategoryResponse = incomeCategoryResponse;
    }
}
