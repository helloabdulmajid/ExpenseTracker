package com.abdulmajid.expensetracker.enums;

import com.abdulmajid.expensetracker.exception.custom.InvalidEnumException;
import com.fasterxml.jackson.annotation.JsonCreator;

public enum PaymentMode {
    CARD,
    UPI,
    BANK_TRANSFER,
    ONLINE,
    CASH;

    @JsonCreator
    public static PaymentMode fromValue(String value) {
        for (PaymentMode mode : PaymentMode.values()) {
            if (mode.name().equalsIgnoreCase(value)) {
                return mode;
            }
        }
        throw new InvalidEnumException("Invalid payment mode. Allowed values: CASH, CARD, UPI,BANK_TRANSFER");
    }
}
