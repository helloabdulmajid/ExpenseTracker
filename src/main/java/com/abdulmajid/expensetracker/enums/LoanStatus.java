package com.abdulmajid.expensetracker.enums;

import com.abdulmajid.expensetracker.exception.custom.InvalidEnumException;
import com.fasterxml.jackson.annotation.JsonCreator;

public enum LoanStatus {
    ACTIVE,
    PENDING,
    CLOSED,
    UNUSED;

    @JsonCreator
    public static PaymentMode fromValue(String value) {
        for (PaymentMode mode : PaymentMode.values()) {
            if (mode.name().equalsIgnoreCase(value)) {
                return mode;
            }
        }
        throw new InvalidEnumException("Invalid Loan Status. At least choose UNUSED ");
    }
}
