package com.project.rentAndShareApp.booking.dto;

import lombok.Getter;

@Getter
public class ShortBookingDto {
    Long id;
    Long bookerId;

    public ShortBookingDto(Long id, Long bookerId) {
        this.id = id;
        this.bookerId = bookerId;
    }
}