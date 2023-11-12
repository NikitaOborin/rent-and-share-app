package com.project.rentAndShareApp.item.dto;

import com.project.rentAndShareApp.booking.dto.ShortBookingDto;
import lombok.Getter;

@Getter
public class ItemWithBookingInfoDto {
    Long id;
    String name;
    String description;
    Boolean available;
    ShortBookingDto lastBooking;
    ShortBookingDto nextBooking;

    public ItemWithBookingInfoDto(Long id,
                                  String name,
                                  String description,
                                  Boolean available,
                                  ShortBookingDto lastBooking,
                                  ShortBookingDto nextBooking) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.available = available;
        this.lastBooking = lastBooking;
        this.nextBooking = nextBooking;
    }
}