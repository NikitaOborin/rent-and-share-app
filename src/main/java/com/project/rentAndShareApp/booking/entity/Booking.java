package com.project.rentAndShareApp.booking.entity;

import com.project.rentAndShareApp.item.entity.Item;
import com.project.rentAndShareApp.user.entity.User;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Booking {
    private Long id;
    private LocalDateTime start;
    private LocalDateTime end;
    private Item item;
    private User booker;
    private BookingStatus status;

    public Booking() {

    }

    public Booking(Long id, LocalDateTime start, LocalDateTime end, Item item, User booker, BookingStatus status) {
        this.id = id;
        this.start = start;
        this.end = end;
        this.item = item;
        this.booker = booker;
        this.status = status;
    }
}
