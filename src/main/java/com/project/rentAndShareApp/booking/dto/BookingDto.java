package com.project.rentAndShareApp.booking.dto;

import com.project.rentAndShareApp.booking.entity.BookingStatus;
import com.project.rentAndShareApp.item.entity.Item;
import com.project.rentAndShareApp.user.entity.User;
import lombok.Value;

import javax.validation.constraints.Future;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Value
public class BookingDto {
    Long id;

    Long itemId;

    @NotNull
    @FutureOrPresent
    LocalDateTime start;

    @NotNull
    @FutureOrPresent
    LocalDateTime end;

    BookingItemDto item;
    BookingBookerDto booker;
    BookingStatus status;
}