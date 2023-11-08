package com.project.rentAndShareApp.booking.service;

import com.project.rentAndShareApp.booking.entity.Booking;
import com.project.rentAndShareApp.booking.entity.BookingStatus;
import com.project.rentAndShareApp.booking.repository.BookingRepository;
import com.project.rentAndShareApp.exception.BookingStartEndTimeNotValidException;
import com.project.rentAndShareApp.exception.ItemNotAvailableNowException;
import com.project.rentAndShareApp.exception.NotFoundException;
import com.project.rentAndShareApp.item.repository.ItemRepository;
import com.project.rentAndShareApp.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

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
        Long userId = booking.getBooker().getId();

        if (isNotValidStartEndTime(booking)) {
            throw new BookingStartEndTimeNotValidException("booking start/end time not valid");
        }

        if (!userRepository.existsById(userId)) {
            throw new NotFoundException("user with id=" + userId + " not found");
        }

        Long itemId = booking.getItem().getId();

        if (!itemRepository.existsById(itemId)) {
            throw new NotFoundException("item with id=" + itemId + " not found");
        }

        if (!itemRepository.existsByIdAndAvailableTrue(itemId)) {
            throw new ItemNotAvailableNowException("item with id=" + itemId + " not available now");
        }

        booking.setStatus(BookingStatus.WAITING);

        booking.setBooker(userRepository.getReferenceById(userId));

        booking.setItem(itemRepository.getReferenceById(itemId));

        return bookingRepository.save(booking);
    }

    private boolean isNotValidStartEndTime(Booking booking) {
        LocalDateTime start = booking.getStart();
        LocalDateTime end = booking.getEnd();

        return start.equals(end) || end.isBefore(start);
    }
}