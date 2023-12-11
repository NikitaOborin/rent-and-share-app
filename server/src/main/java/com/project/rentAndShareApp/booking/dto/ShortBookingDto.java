package com.project.rentAndShareApp.booking.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShortBookingDto {
    private Long id;
    private Long bookerId;

    public ShortBookingDto(Long id, Long bookerId) {
        this.id = id;
        this.bookerId = bookerId;
    }
}