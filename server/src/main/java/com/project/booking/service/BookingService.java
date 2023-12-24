package com.project.booking.service;

import com.project.booking.dto.BookingRequestDto;
import com.project.booking.dto.BookingResponseDto;

import java.util.List;

public interface BookingService {
    BookingResponseDto addBooking(BookingRequestDto bookingDto, Long bookerId);

    BookingResponseDto approveBookingByOwnerId(Long bookingId, Boolean approve, Long ownerId);

    BookingResponseDto getBookingById(Long bookingId, Long userId);

    List<BookingResponseDto> getListBookingByBookerIdAndState(Long bookerId, String stateString, Integer from, Integer size);

    List<BookingResponseDto> getListBookingByOwnerIdAndState(Long ownerId, String stateString, Integer from, Integer size);
}