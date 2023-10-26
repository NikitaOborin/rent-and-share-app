package com.project.rentAndShareApp.booking.dto;

import com.project.rentAndShareApp.booking.entity.BookingStatus;
import com.project.rentAndShareApp.item.entity.Item;
import com.project.rentAndShareApp.user.entity.User;
import lombok.Value;

import java.time.LocalDateTime;

@Value
public class BookingDto {
    Long id;
    LocalDateTime start;
    LocalDateTime end;
    Item item;
    User booker;
    BookingStatus status;
}
