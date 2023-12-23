package com.project.booking.repository;

import com.project.booking.entity.Booking;
import com.project.booking.entity.BookingStatus;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> getByBookerIdOrderByStartDesc(Long bookerId, Pageable pageable);

    List<Booking> getByBookerIdAndStartBeforeAndEndAfterOrderByStartDesc(
            Long bookerId, LocalDateTime currentTime, LocalDateTime otherCurrentTime, Pageable pageable);

    List<Booking> getByBookerIdAndEndBeforeOrderByStartDesc(
            Long bookerId, LocalDateTime currentTime, Pageable pageable);

    List<Booking> getByBookerIdAndStartAfterOrderByStartDesc(
            Long bookerId, LocalDateTime currentTime, Pageable pageable);

    List<Booking> getByBookerIdAndStatusEqualsOrderByStartDesc(Long booking, BookingStatus status, Pageable pageable);

    List<Booking> getByItemOwnerIdOrderByStartDesc(Long ownerId, Pageable pageable);

    List<Booking> getByItemOwnerIdAndStartBeforeAndEndAfterOrderByStartDesc(
            Long ownerId, LocalDateTime currentTime, LocalDateTime otherCurrentTime, Pageable pageable);

    List<Booking> getByItemOwnerIdAndEndBeforeOrderByStartDesc(
            Long ownerId, LocalDateTime currentTime, Pageable pageable);

    List<Booking> getByItemOwnerIdAndStartAfterOrderByStartDesc(
            Long ownerId, LocalDateTime currentTime, Pageable pageable);

    List<Booking> getByItemOwnerIdAndStatusEqualsOrderByStartDesc(Long ownerId, BookingStatus status, Pageable pageable);

    Booking getFirstByItemIdAndStartBeforeAndStatusNotOrderByEndDesc(
            Long itemId, LocalDateTime currentTime, BookingStatus status);

    Booking getFirstByItemIdAndStartAfterAndStatusNotOrderByEnd(
            Long itemId, LocalDateTime currentTime, BookingStatus status);
}