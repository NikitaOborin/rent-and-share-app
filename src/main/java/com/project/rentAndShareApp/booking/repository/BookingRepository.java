package com.project.rentAndShareApp.booking.repository;

import com.project.rentAndShareApp.booking.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> getBookingsByBookerIdOrderByStartDesc(Long bookerId);

    List<Booking> getBookingsByItemOwnerIdOrderByStartDesc(Long ownerId);
}