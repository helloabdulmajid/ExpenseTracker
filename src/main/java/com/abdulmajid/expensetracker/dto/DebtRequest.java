package com.abdulmajid.expensetracker.dto;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.util.Date;

public class DebtRequest {
    private Integer id;

    @NotNull(message = "Amount cannot be null")
    @DecimalMin(value = "0.01", message = "Amount must be greater than zero")
    private BigDecimal amount;

    @NotBlank(message = "Creditor is required")
    private String creditor; // Who gives the debt (Credit card, Bank, Person)

    @NotBlank(message = "Creditor name is required")
    private String creditorName; // Example: Axis Flipkart CC, IDFC, BOB

    @NotBlank(message = "Debtor is required")
    private String debtor; // Who owes the debt

    @NotBlank(message = "Debtor name is required")
    private String debtorName; // Name of the debtor

    @NotNull(message = "Due date is required")
    @FutureOrPresent(message = "Due date must be today or in the future")
    private Date dueDate;

    @NotBlank(message = "Status is required")
    @Pattern(regexp = "OUTSTANDING|PAID|OVERDUE", message = "Status must be OUTSTANDING, PAID, or OVERDUE")
    private String status; // OUTSTANDING, PAID, OVERDUE

    @NotBlank(message = "Priority is required")
    @Pattern(regexp = "HIGH|MEDIUM|LOW", message = "Priority must be HIGH, MEDIUM, or LOW")
    private String priority; // HIGH, MEDIUM, LOW

    @NotNull(message = "Recurring flag is required")
    private Boolean isRecurring; // True for recurring debts like credit card EMI

    @NotBlank(message = "Category is required")
    private String category; // Medical, Utility, Credit Card, etc.

    private String note; // Optional

    @NotBlank(message = "Day is required")
    private String day; // Example: Monday, Tuesday, etc.

    @NotBlank(message = "Date is required")
    private String date; // Example: "2024-03-02"
    private Date createdAt;
    private Date updatedAt;

    public DebtRequest() {
    }

    public DebtRequest(BigDecimal amount, String creditor, String creditorName, String debtor,
                       String debtorName, Date dueDate, String status, String priority,
                       Boolean isRecurring, String category, String note, String day, String date) {
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
        this.createdAt = new Date();
        this.updatedAt = new Date();
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
}
