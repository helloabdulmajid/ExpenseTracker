package com.abdulmajid.expensetracker.model;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "expenses")
public class Expense {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer amount;
    private String paymentMode;
    private String note;
    private String day;
    private String date;
    private Date createdAt;
    private Date updatedAt;

    @JsonManagedReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @JsonManagedReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;


    public Expense() {
    }

//    public Expense(Integer id, Integer amount, String paymentMode, String note, String day, String date, Date createdAt, Date updatedAt, Category category, User user) {
//        this.id = id;
//        this.amount = amount;
//        this.paymentMode = paymentMode;
//        this.note = note;
//        this.day = day;
//        this.date = date;
//        this.createdAt = createdAt;
//        this.updatedAt = updatedAt;
//        this.category = category;
//        this.user = user;
//    }

    public Expense(Integer amount, String paymentMode, String note, String day, String date, Date createdAt, Date updatedAt, Category dbCategory, User existsUser) {
        this.amount = amount;
        this.paymentMode = paymentMode;
        this.note = note;
        this.day = day;
        this.date = date;
        this.createdAt = new Date();
        this.updatedAt = new Date();
        this.category = dbCategory;
        this.user = existsUser;
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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
