package com.project.rentAndShareApp.booking.controller;

import com.project.rentAndShareApp.booking.dto.BookingRequestDto;
import com.project.rentAndShareApp.booking.dto.BookingResponseDto;
import com.project.rentAndShareApp.booking.service.BookingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@Validated
@RestController
@RequestMapping("/bookings")
public class BookingController {
    private final BookingService bookingService;

    @Autowired
    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping()
    public BookingResponseDto addBooking(@RequestBody @Valid BookingRequestDto bookingDto,
                                         @RequestHeader("X-Sharer-User-Id") Long userId) {
        log.info("BookingController: addBooking(): start with bookerId={} and bookingDto='{}'", userId, bookingDto);
        return bookingService.addBooking(bookingDto, userId);
    }

    @PatchMapping("/{bookingId}")
    public BookingResponseDto approveBookingByUserId(@PathVariable Long bookingId,
                                                     @RequestHeader("X-Sharer-User-Id") Long userId,
                                                     @RequestParam Boolean approved) {
        log.info("BookingController: approveBookingByUserId(): start with bookingId={} and ownerId={}", bookingId, userId);
        return bookingService.approveBookingByUserId(bookingId, approved, userId);
    }

    @GetMapping("/{bookingId}")
    public BookingResponseDto getBookingById(@PathVariable Long bookingId,
                                             @RequestHeader("X-Sharer-User-Id") Long userId) {
        log.info("BookingController: getBookingById(): start with bookingId={} and userId={}", bookingId, userId);
        return bookingService.getBookingById(bookingId, userId);
    }

    @GetMapping()
    public List<BookingResponseDto> getListBookingByBookerIdAndState(
            @RequestHeader("X-Sharer-User-Id") Long bookerId,
            @RequestParam(required = false, defaultValue = "ALL") String state) {
        log.info("BookingController: getListBookingByBookerIdAndState(): " +
                "start with bookerId={} and state='{}'", bookerId, state);

        return bookingService.getListBookingByBookerIdAndState(bookerId, state);
    }

    @GetMapping("/owner")
    public List<BookingResponseDto> getListBookingByOwnerIdAndState(
            @RequestHeader("X-Sharer-User-Id") Long ownerId,
            @RequestParam(required = false, defaultValue = "ALL") String state) {
        log.info("BookingController: getListBookingByOwnerIdAndState(): " +
                "start with ownerId={} and state='{}'", ownerId, state);
        return bookingService.getListBookingByOwnerIdAndState(ownerId, state);
    }
}