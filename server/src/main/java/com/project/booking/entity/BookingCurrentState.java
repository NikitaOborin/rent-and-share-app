package com.project.booking.entity;

import com.project.exception.BookingUnsupportedStatusException;

public enum BookingCurrentState {
    ALL,
    CURRENT,
    PAST,
    FUTURE,
    WAITING,
    REJECTED;

    public static BookingCurrentState from(String stringState) {
        BookingCurrentState state = null;

        for (BookingCurrentState value : BookingCurrentState.values()) {
            if (value.toString().equalsIgnoreCase(stringState)) {
                state = value;
            }
        }

        if (state == null) {
            throw new BookingUnsupportedStatusException("booking not have state='" + stringState + "'");
        }

        return state;
    }
}