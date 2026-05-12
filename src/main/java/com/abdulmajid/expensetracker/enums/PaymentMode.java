package com.abdulmajid.expensetracker.enums;

import com.abdulmajid.expensetracker.exception.custom.InvalidEnumException;
import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.Arrays;
import java.util.stream.Collectors;

public enum PaymentMode {

    CARD,
    UPI,
    BANK_TRANSFER,
    ONLINE,
    CASH;

    @JsonCreator
    public static PaymentMode fromValue(
            String value
    ) {

        for (PaymentMode mode : PaymentMode.values()) {

            if (mode.name().equalsIgnoreCase(value)) {

                return mode;
            }
        }

        String allowedValues =
                Arrays.stream(PaymentMode.values())
                        .map(Enum::name)
                        .collect(Collectors.joining(", "));

        throw new InvalidEnumException(
                "Invalid payment mode. Allowed values: "
                        + allowedValues
        );
    }
}