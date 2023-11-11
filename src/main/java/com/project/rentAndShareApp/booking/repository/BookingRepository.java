package com.project.rentAndShareApp.booking.repository;

import com.project.rentAndShareApp.booking.entity.Booking;
import com.project.rentAndShareApp.booking.entity.BookingCurrentState;
import com.project.rentAndShareApp.booking.entity.BookingStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> getByBookerIdOrderByStartDesc(Long bookerId);

    List<Booking> getByBookerIdAndStartBeforeAndEndAfterOrderByStartDesc(
            Long bookerId, LocalDateTime currentTime, LocalDateTime otherCurrentTime);

    List<Booking> getByBookerIdAndEndBeforeOrderByStartDesc(
            Long bookerId, LocalDateTime currentTime);

    List<Booking> getByBookerIdAndStartAfterOrderByStartDesc(
            Long bookerId, LocalDateTime currentTime);

    List<Booking> getByBookerIdAndStatusEqualsOrderByStartDesc(Long booking, BookingStatus status);

    List<Booking> getByItemOwnerIdOrderByStartDesc(Long ownerId);

    List<Booking> getByItemOwnerIdAndStartBeforeAndEndAfterOrderByStartDesc(
            Long ownerId, LocalDateTime currentTime, LocalDateTime otherCurrentTime);

    List<Booking> getByItemOwnerIdAndEndBeforeOrderByStartDesc(
            Long ownerId, LocalDateTime currentTime);

    List<Booking> getByItemOwnerIdAndStartAfterOrderByStartDesc(
            Long ownerId, LocalDateTime currentTime);

    List<Booking> getByItemOwnerIdAndStatusEqualsOrderByStartDesc(Long ownerId, BookingStatus status);
}