package com.abdulmajid.expensetracker.dto;

import com.abdulmajid.expensetracker.model.User;

import java.util.Date;

public class IncomeResponse {
    private Integer id;
    private Integer amount;
    private String source;
    private String receiveMode;
    private String note;
    private String day;
    private String date;
    private Date createdAt;
    private Date updatedAt;
    private User user;

    public IncomeResponse() {
    }

    public IncomeResponse(Integer id, Integer amount, String source, String receiveMode,
                          String note, String day, String date, Date createdAt, Date updatedAt, User user) {
        this.id = id;
        this.amount = amount;
        this.source = source;
        this.receiveMode = receiveMode;
        this.note = note;
        this.day = day;
        this.date = date;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
