package com.abdulmajid.expensetracker.dto;

import com.abdulmajid.expensetracker.enums.LoanStatus;
import com.abdulmajid.expensetracker.enums.LoanType;
import com.abdulmajid.expensetracker.enums.PaymentMode;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.util.Date;

public class LoanRequest {
    private Integer id;

    @NotNull(message = "Amount cannot be null")
    @DecimalMin(value = "0.01", message = "Amount must be greater than zero")
    private BigDecimal amount;

    @NotBlank(message = "Lender name cannot be empty")
    @Size(max = 100, message = "Lender name must be within 100 characters")
    private String lender;

    @Size(max = 100, message = "Borrower name must be within 100 characters")
    private String borrower; // Optional

    @NotNull(message = "Interest rate cannot be null")
    @DecimalMin(value = "0.0", inclusive = false, message = "Interest rate must be greater than 0")
    @DecimalMax(value = "100.0", message = "Interest rate cannot be more than 100%")
    private BigDecimal interestRate;

    @NotNull(message = "Loan type cannot be null")
    private LoanType loanType;

    @NotNull(message = "Tenure cannot be null")
    @Min(value = 1, message = "Tenure must be at least 1 month")
    @Max(value = 600, message = "Tenure cannot be more than 50 years (600 months)")
    private Integer tenureMonths;

    @NotNull(message = "Start date cannot be null")
    @PastOrPresent(message = "Start date must be today or in the past")
    private Date startDate;

    @NotNull(message = "Due date cannot be null")
    @Future(message = "Due date must be in the future")
    private Date dueDate;

    @NotNull(message = "Please choose a payment mode")
    private PaymentMode paymentMode;

    @NotBlank(message = "Payment status cannot be empty")
    @Pattern(regexp = "PAID|UNPAID", message = "Invalid value for isPaid. Allowed: PAID, UNPAID")
    private String isPaid;

    @NotNull(message = "Remaining balance cannot be null")
    @DecimalMin(value = "0.00", message = "Remaining balance must be at least 0")
    private BigDecimal remainingBalance;

    @NotNull(message = "Loan status cannot be null")
    private LoanStatus status;

    @Size(max = 255, message = "Note cannot exceed 255 characters")
    private String note;

    @NotNull(message = "Day of the week cannot be null")
    private DayOfWeek day;

    @NotNull(message = "Date cannot be null")
    private Date date;


    public LoanRequest() {
    }

    public LoanRequest(BigDecimal amount, String lender, String borrower, BigDecimal interestRate, LoanType loanType,
                       Integer tenureMonths, Date startDate, Date dueDate, PaymentMode paymentMode, String isPaid,
                       BigDecimal remainingBalance, LoanStatus status, String note, DayOfWeek day, Date date) {
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
}
