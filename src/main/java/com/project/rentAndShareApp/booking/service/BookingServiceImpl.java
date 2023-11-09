package com.project.rentAndShareApp.booking.service;

import com.project.rentAndShareApp.booking.entity.Booking;
import com.project.rentAndShareApp.booking.entity.BookingStatus;
import com.project.rentAndShareApp.booking.repository.BookingRepository;
import com.project.rentAndShareApp.exception.BookingStartEndTimeNotValidException;
import com.project.rentAndShareApp.exception.ItemAvailableException;
import com.project.rentAndShareApp.exception.NotFoundException;
import com.project.rentAndShareApp.item.entity.Item;
import com.project.rentAndShareApp.item.repository.ItemRepository;
import com.project.rentAndShareApp.user.entity.User;
import com.project.rentAndShareApp.user.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
public class BookingServiceImpl implements BookingService {
    private final BookingRepository bookingRepository;
    private final UserRepository userRepository;
    private final ItemRepository itemRepository;

    @Autowired
    public BookingServiceImpl(BookingRepository bookingRepository, UserRepository userRepository, ItemRepository itemRepository) {
        this.bookingRepository = bookingRepository;
        this.userRepository = userRepository;
        this.itemRepository = itemRepository;
    }

    @Override
    public Booking addBooking(Booking booking) {
        log.info("BookingService: addBooking(): start with booking='{}'", booking);
        Long bookerId = booking.getBooker().getId();
        Long itemId = booking.getItem().getId();

        if (isNotValidStartEndTime(booking)) {
            throw new BookingStartEndTimeNotValidException("booking start/end time not valid");
        }

        if (!userRepository.existsById(bookerId)) {
            throw new NotFoundException("booker with id=" + bookerId + " not found");
        }

        if (!itemRepository.existsById(itemId)) {
            throw new NotFoundException("item with id=" + itemId + " not found");
        }

        User booker = userRepository.getReferenceById(bookerId);
        Item item = itemRepository.getReferenceById(itemId);

        if (item.getOwner().getId().equals(bookerId)) {
            throw new NotFoundException("booker with id=" + bookerId + " can not booking this item, he is owner");
        }

        if (item.getAvailable().equals(false)) {
            throw new ItemAvailableException("item with id=" + itemId + " not available now");
        }

        booking.setStatus(BookingStatus.WAITING);
        booking.setBooker(booker);
        booking.setItem(item);

        return bookingRepository.save(booking);
    }

    @Override
    public Booking approveBookingByUserId(Long bookingId, Boolean approve, Long ownerId) {
        log.info("BookingService: approveBookingByUserId(): start with bookingId={} and ownerId={}", bookingId, ownerId);
        if (!bookingRepository.existsById(bookingId)) {
            throw new NotFoundException("booking with id=" + bookingId + " not found");
        }

        Booking booking = bookingRepository.getReferenceById(bookingId);

        if (!booking.getItem().getOwner().getId().equals(ownerId)) {
            throw new NotFoundException("user with id=" + ownerId + " not owner for item with id=" + booking.getItem().getId());
        }

        if (booking.getStatus().equals(BookingStatus.APPROVED)) {
            throw new ItemAvailableException("booking with id=" + bookingId + " already approved");
        }

        if (approve) {
            booking.setStatus(BookingStatus.APPROVED);
        } else {
            booking.setStatus(BookingStatus.REJECTED);
        }

        return bookingRepository.save(booking);
    }

    @Override
    public Booking getBookingById(Long bookingId, Long userId) {
        log.info("BookingService: getBookingById(): start with bookingId={} and userId={}", bookingId, userId);
        if (!bookingRepository.existsById(bookingId)) {
            throw new NotFoundException("booking with id=" + bookingId + " not found");
        }

        if (!userRepository.existsById(userId)) {
            throw new NotFoundException("user with id=" + userId + " not found");
        }

        Booking booking = bookingRepository.getReferenceById(bookingId);

        if (!booking.getBooker().getId().equals(userId) || !booking.getItem().getOwner().getId().equals(userId)) {
            throw new NotFoundException("user with id=" + userId +" not owner and not booker for booking with id=" + bookingId);
        }

        return booking;
    }

    private boolean isNotValidStartEndTime(Booking booking) {
        LocalDateTime start = booking.getStart();
        LocalDateTime end = booking.getEnd();

        return start.equals(end) || end.isBefore(start);
    }
}