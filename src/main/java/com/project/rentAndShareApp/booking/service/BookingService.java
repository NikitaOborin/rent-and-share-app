package com.project.rentAndShareApp.booking.service;

import com.project.rentAndShareApp.booking.dto.BookingRequestDto;
import com.project.rentAndShareApp.booking.dto.BookingResponseDto;
import com.project.rentAndShareApp.booking.entity.Booking;
import com.project.rentAndShareApp.booking.entity.BookingCurrentState;

import java.util.List;

public interface BookingService {
    BookingResponseDto addBooking(BookingRequestDto bookingDto, Long userId);

    BookingResponseDto approveBookingByUserId(Long bookingId, Boolean approve, Long userId);

    Booking getBookingById(Long bookingId, Long userId);

    List<Booking> getListBookingByBookerIdAndState(Long bookerId, String stateString);

    List<Booking> getListBookingByOwnerIdAndState(Long ownerId, String stateString);
}