package com.abdulmajid.expensetracker.model;

import com.abdulmajid.expensetracker.enums.LoanStatus;
import com.abdulmajid.expensetracker.enums.LoanType;
import com.abdulmajid.expensetracker.enums.PaymentMode;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.util.Date;

@Entity
@Table(name = "loans")
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private BigDecimal amount;
    private String lender;  // Who provided the loan
    private String borrower; // Optional, if applicable
    private BigDecimal interestRate;
    @Enumerated(EnumType.STRING)
    private LoanType loanType; // e.g., Personal, Business, Education
    private Integer tenureMonths; // Duration in months
    private Date startDate;
    private Date dueDate;
    @Enumerated(EnumType.STRING)
    private PaymentMode paymentMode; // CASH, BANK_TRANSFER, ONLINE
    private String isPaid;
    private BigDecimal remainingBalance;
    @Enumerated(EnumType.STRING)
    private LoanStatus status; // ACTIVE, PENDING, CLOSED
    private String note;
    @Enumerated(EnumType.STRING)
    private DayOfWeek day;
    private Date date;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt = new Date();

    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt = new Date();

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Loan() {
    }

    public Loan(BigDecimal amount, String lender, String borrower, BigDecimal interestRate, LoanType loanType,
                Integer tenureMonths, Date startDate, Date dueDate, PaymentMode paymentMode, String isPaid,
                BigDecimal remainingBalance, LoanStatus status, String note, DayOfWeek day, Date date, User user) {
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
        this.user = user;
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
