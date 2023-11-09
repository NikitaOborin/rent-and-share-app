package com.project.rentAndShareApp.booking.repository;

import com.project.rentAndShareApp.booking.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Long> {
}