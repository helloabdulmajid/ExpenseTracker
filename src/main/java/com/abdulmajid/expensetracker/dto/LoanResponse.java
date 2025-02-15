package com.abdulmajid.expensetracker.dto;

import com.abdulmajid.expensetracker.enums.LoanStatus;
import com.abdulmajid.expensetracker.enums.LoanType;
import com.abdulmajid.expensetracker.enums.PaymentMode;
import com.abdulmajid.expensetracker.model.User;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.util.Date;

public class LoanResponse {
    private Integer id;
    private BigDecimal amount;
    private String lender;  // Who provided the loan
    private String borrower; // Optional, if applicable
    private BigDecimal interestRate;
    private LoanType loanType;
    private Integer tenureMonths;
    private Date startDate;
    private Date dueDate;
    private PaymentMode paymentMode;
    private String isPaid;
    private BigDecimal remainingBalance;
    private LoanStatus status;
    private String note;
    private DayOfWeek day;
    private Date date;
    private Date createdAt;
    private Date updatedAt;
    private User user;

    public LoanResponse() {
    }

    public LoanResponse(Integer id, BigDecimal amount, String lender, String borrower, BigDecimal interestRate,
                        LoanType loanType, Integer tenureMonths, Date startDate, Date dueDate,
                        PaymentMode paymentMode, String isPaid, BigDecimal remainingBalance, LoanStatus status,
                        String note, DayOfWeek day, Date date, Date createdAt, Date updatedAt, User user) {
        this.id = id;
        this.amount = amount;
        this.lender = lender;
        this.borrower = borrower;
        this.interestRate = interestRate;
        this.loanType = loanType;
        this.tenureMonths = tenureMonths;
        this.startDate = startDate;
        this.dueDate = dueDate;
        this.paymentMode = paymentMode;
        this.isPaid = isPaid;
        this.remainingBalance = remainingBalance;
        this.status = status;
        this.note = note;
        this.day = day;
        this.date = date;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.user = user;
    }

    public LoanResponse(Integer id, BigDecimal amount, String lender, String borrower, BigDecimal interestRate,
                        LoanType loanType, Integer tenureMonths, Date startDate, Date dueDate,
                        PaymentMode paymentMode, String isPaid, BigDecimal remainingBalance,
                        LoanStatus status, String note, DayOfWeek day, Date date, Date createdAt,
                        Date updatedAt) {
        this.id = id;
        this.amount = amount;
        this.lender = lender;
        this.borrower = borrower;
        this.interestRate = interestRate;
        this.loanType = loanType;
        this.tenureMonths = tenureMonths;
        this.startDate = startDate;
        this.dueDate = dueDate;
        this.paymentMode = paymentMode;
        this.isPaid = isPaid;
        this.remainingBalance = remainingBalance;
        this.status = status;
        this.note = note;
        this.day = day;
        this.date = date;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
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

    public String getLender() {
        return lender;
    }

    public void setLender(String lender) {
        this.lender = lender;
    }

    public String getBorrower() {
        return borrower;
    }

    public void setBorrower(String borrower) {
        this.borrower = borrower;
    }

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(BigDecimal interestRate) {
        this.interestRate = interestRate;
    }

    public LoanType getLoanType() {
        return loanType;
    }

    public void setLoanType(LoanType loanType) {
        this.loanType = loanType;
    }

    public Integer getTenureMonths() {
        return tenureMonths;
    }

    public void setTenureMonths(Integer tenureMonths) {
        this.tenureMonths = tenureMonths;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public PaymentMode getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(PaymentMode paymentMode) {
        this.paymentMode = paymentMode;
    }

    public String getIsPaid() {
        return isPaid;
    }

    public void setIsPaid(String isPaid) {
        this.isPaid = isPaid;
    }

    public BigDecimal getRemainingBalance() {
        return remainingBalance;
    }

    public void setRemainingBalance(BigDecimal remainingBalance) {
        this.remainingBalance = remainingBalance;
    }

    public LoanStatus getStatus() {
        return status;
    }

    public void setStatus(LoanStatus status) {
        this.status = status;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public DayOfWeek getDay() {
        return day;
    }

    public void setDay(DayOfWeek day) {
        this.day = day;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
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
