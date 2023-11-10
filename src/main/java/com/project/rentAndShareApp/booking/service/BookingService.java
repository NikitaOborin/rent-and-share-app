package com.project.rentAndShareApp.booking.service;

import com.project.rentAndShareApp.booking.entity.Booking;
import com.project.rentAndShareApp.booking.entity.BookingCurrentState;

import java.util.List;

public interface BookingService {
    Booking addBooking(Booking booking);

    Booking approveBookingByUserId(Long bookingId, Boolean approve, Long userId);

    Booking getBookingById(Long bookingId, Long userId);

    List<Booking> getListBookingByBookerIdAndState(Long bookerId, String stateString);

    List<Booking> getListBookingByOwnerIdAndState(Long ownerId, String stateString);
}