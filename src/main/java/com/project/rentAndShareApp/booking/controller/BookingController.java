package com.project.rentAndShareApp.booking.controller;

import com.project.rentAndShareApp.booking.dto.BookingRequestDto;
import com.project.rentAndShareApp.booking.dto.BookingResponseDto;
import com.project.rentAndShareApp.booking.entity.Booking;
import com.project.rentAndShareApp.booking.mapper.BookingMapper;
import com.project.rentAndShareApp.booking.service.BookingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@Validated
@RestController
@RequestMapping("/bookings")
public class BookingController {
    private final BookingService bookingService;
    private final BookingMapper bookingMapper;

    @Autowired
    public BookingController(BookingService bookingService, BookingMapper bookingMapper) {
        this.bookingService = bookingService;
        this.bookingMapper = bookingMapper;
    }

    @PostMapping()
    public BookingResponseDto addBooking(@RequestBody @Valid BookingRequestDto bookingDto,
                                         @RequestHeader("X-Sharer-User-Id") Long userId) {
        log.info("BookingController: addBooking(): start with userId={} and bookingDto='{}'", userId, bookingDto);
        Booking booking = bookingService.addBooking(bookingMapper.toBooking(bookingDto, userId));

        return bookingMapper.toBookingDto(booking);
    }
}