package com.project.rentAndShareApp.booking.service;

import com.project.rentAndShareApp.booking.entity.Booking;
import com.project.rentAndShareApp.booking.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookingServiceImpl implements BookingService {
    private final BookingRepository bookingRepository;

    @Autowired
    public BookingServiceImpl(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    @Override
    public Booking addBooking(Booking booking) {
        return bookingRepository.save(booking);
    }
}
