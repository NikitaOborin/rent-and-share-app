package com.project.rentAndShareApp.booking.service;

import com.project.rentAndShareApp.booking.entity.Booking;

public interface BookingService {
    Booking addBooking(Booking booking);

    Booking approveBookingByUserId(Long bookingId, Boolean approve, Long userId);

    Booking getBookingById(Long bookingId, Long userId);
}