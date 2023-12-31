package com.project.rentAndShareApp.booking.service;

import com.project.rentAndShareApp.booking.dto.BookingRequestDto;
import com.project.rentAndShareApp.booking.dto.BookingResponseDto;

import java.util.List;

public interface BookingService {
    BookingResponseDto addBooking(BookingRequestDto bookingDto, Long userId);

    BookingResponseDto approveBookingByUserId(Long bookingId, Boolean approve, Long userId);

    BookingResponseDto getBookingById(Long bookingId, Long userId);

    List<BookingResponseDto> getListBookingByBookerIdAndState(Long bookerId, String stateString, Integer from, Integer size);

    List<BookingResponseDto> getListBookingByOwnerIdAndState(Long ownerId, String stateString, Integer from, Integer size);
}