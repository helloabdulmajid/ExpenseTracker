package com.abdulmajid.expensetracker.enums;

import com.abdulmajid.expensetracker.exception.custom.InvalidEnumException;
import com.fasterxml.jackson.annotation.JsonCreator;

public enum LoanType {
    PERSONAL,
    BUSINESS,
    HOME,
    EDUCATION,
    GOLD,
    VEHICLE,
    MEDICAL,
    AGRICULTURE,
    STARTUP,
    OTHERS;

    @JsonCreator
    public static PaymentMode fromValue(String value) {
        for (PaymentMode mode : PaymentMode.values()) {
            if (mode.name().equalsIgnoreCase(value)) {
                return mode;
            }
        }
        throw new InvalidEnumException("Invalid Loan Type. At least choose OTHERS ");
    }
}
