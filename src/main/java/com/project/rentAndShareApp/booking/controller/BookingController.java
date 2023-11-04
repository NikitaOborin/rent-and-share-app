package com.project.rentAndShareApp.booking.controller;

import com.project.rentAndShareApp.booking.dto.BookingDto;
import com.project.rentAndShareApp.booking.entity.Booking;
import com.project.rentAndShareApp.booking.mapper.BookingMapper;
import com.project.rentAndShareApp.booking.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/bookings")
public class BookingController {
    private final BookingService bookingService;
    private final BookingMapper bookingMapper;

    @Autowired
    public BookingController(BookingService bookingService, BookingMapper bookingMapper) {
        this.bookingService = bookingService;
        this.bookingMapper = bookingMapper;
    }

    @PostMapping()
    public BookingDto addBooking(@RequestBody BookingDto bookingDto) {
        Booking booking = bookingService.addBooking(bookingMapper.toBooking(bookingDto));

        return bookingMapper.toBookingDto(booking);
    }
}
