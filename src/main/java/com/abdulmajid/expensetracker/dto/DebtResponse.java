package com.abdulmajid.expensetracker.dto;

import com.abdulmajid.expensetracker.model.User;

import java.math.BigDecimal;
import java.util.Date;

public class DebtResponse {
    private Integer id;
    private BigDecimal amount;
    private String creditor;
    private String creditorName;
    private String debtor;
    private String debtorName;
    private Date dueDate;
    private String status;
    private String priority;
    private Boolean isRecurring;
    private String category;
    private String note;
    private String day;
    private String date;
    private Date createdAt;
    private Date updatedAt;
    private User user;

    public DebtResponse() {
    }

    public DebtResponse(Integer id, BigDecimal amount, String creditor, String creditorName,
                        String debtor, String debtorName, Date dueDate, String status,
                        String priority, Boolean isRecurring, String category, String note,
                        String day, String date, Date createdAt, Date updatedAt, User user) {
        this.id = id;
        this.amount = amount;
        this.creditor = creditor;
        this.creditorName = creditorName;
        this.debtor = debtor;
        this.debtorName = debtorName;
        this.dueDate = dueDate;
        this.status = status;
        this.priority = priority;
        this.isRecurring = isRecurring;
        this.category = category;
        this.note = note;
        this.day = day;
        this.date = date;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.user = user;
    }

    public DebtResponse(Integer id, BigDecimal amount, String creditor, String creditorName, String debtor, String debtorName,
                        Date dueDate, String status, String priority, Boolean recurring, String category, String note,
                        String day, String date, Date createdAt, Date updatedAt) {
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

    public String getCreditor() {
        return creditor;
    }

    public void setCreditor(String creditor) {
        this.creditor = creditor;
    }

    public String getCreditorName() {
        return creditorName;
    }

    public void setCreditorName(String creditorName) {
        this.creditorName = creditorName;
    }

    public String getDebtor() {
        return debtor;
    }

    public void setDebtor(String debtor) {
        this.debtor = debtor;
    }

    public String getDebtorName() {
        return debtorName;
    }

    public void setDebtorName(String debtorName) {
        this.debtorName = debtorName;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public Boolean getRecurring() {
        return isRecurring;
    }

    public void setRecurring(Boolean recurring) {
        isRecurring = recurring;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
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
