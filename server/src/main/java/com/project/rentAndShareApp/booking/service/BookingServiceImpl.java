package com.project.rentAndShareApp.booking.service;

import com.project.rentAndShareApp.booking.dto.BookingRequestDto;
import com.project.rentAndShareApp.booking.dto.BookingResponseDto;
import com.project.rentAndShareApp.booking.entity.Booking;
import com.project.rentAndShareApp.booking.entity.BookingCurrentState;
import com.project.rentAndShareApp.booking.entity.BookingStatus;
import com.project.rentAndShareApp.booking.mapper.BookingMapper;
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
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class BookingServiceImpl implements BookingService {
    private final BookingRepository bookingRepository;
    private final UserRepository userRepository;
    private final ItemRepository itemRepository;
    private final BookingMapper bookingMapper;

    @Autowired
    public BookingServiceImpl(BookingRepository bookingRepository,
                              UserRepository userRepository,
                              ItemRepository itemRepository,
                              BookingMapper bookingMapper) {
        this.bookingRepository = bookingRepository;
        this.userRepository = userRepository;
        this.itemRepository = itemRepository;
        this.bookingMapper = bookingMapper;
    }

    @Override
    public BookingResponseDto addBooking(BookingRequestDto bookingDto, Long userId) {
        log.info("BookingService: addBooking(): start with bookerId={} and bookingDto='{}'", userId, bookingDto);
        Booking booking = bookingMapper.toBooking(bookingDto, userId);

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

        return bookingMapper.toBookingDto(bookingRepository.save(booking));
    }

    @Override
    public BookingResponseDto approveBookingByUserId(Long bookingId, Boolean approve, Long ownerId) {
        log.info("BookingService: approveBookingByUserId(): start with bookingId={} " +
                "and ownerId={}", bookingId, ownerId);
        if (!bookingRepository.existsById(bookingId)) {
            throw new NotFoundException("booking with id=" + bookingId + " not found");
        }

        Booking booking = bookingRepository.getReferenceById(bookingId);

        if (!booking.getItem().getOwner().getId().equals(ownerId)) {
            throw new NotFoundException("user with id=" + ownerId + " not owner for item " +
                    "with id=" + booking.getItem().getId());
        }

        if (booking.getStatus().equals(BookingStatus.APPROVED)) {
            throw new ItemAvailableException("booking with id=" + bookingId + " already approved");
        }

        if (approve) {
            booking.setStatus(BookingStatus.APPROVED);
        } else {
            booking.setStatus(BookingStatus.REJECTED);
        }

        return bookingMapper.toBookingDto(bookingRepository.save(booking));
    }

    @Override
    public BookingResponseDto getBookingById(Long bookingId, Long userId) {
        log.info("BookingService: getBookingById(): start with bookingId={} and userId={}", bookingId, userId);
        if (!bookingRepository.existsById(bookingId)) {
            throw new NotFoundException("booking with id=" + bookingId + " not found");
        }

        if (!userRepository.existsById(userId)) {
            throw new NotFoundException("user with id=" + userId + " not found");
        }

        Booking booking = bookingRepository.getReferenceById(bookingId);

        Long bookerId = booking.getBooker().getId();
        Long ownerId = booking.getItem().getOwner().getId();

        if (!bookerId.equals(userId) && !ownerId.equals(userId)) {
            throw new NotFoundException("user with id=" + userId +" not owner " +
                    "and not booker for booking with id=" + bookingId);
        }

        return bookingMapper.toBookingDto(booking);
    }

    @Override
    public List<BookingResponseDto> getListBookingByBookerIdAndState(Long bookerId, String stateString, Integer from, Integer size) {
        log.info("BookingService: getListBookingByBookerIdAndState(): start with bookerId={} " +
                "and state='{}'", bookerId, stateString);
        Pageable pageRequest = PageRequest.of(from / size, size);
        BookingCurrentState state = BookingCurrentState.from(stateString);
        List<Booking> bookings = new ArrayList<>();
        List<BookingResponseDto> bookingDtoList = new ArrayList<>();

        if (!userRepository.existsById(bookerId)) {
            throw new NotFoundException("booker with id=" + bookerId + " not found");
        }

        switch (state) {
            case ALL:
                bookings = bookingRepository.getByBookerIdOrderByStartDesc(bookerId, pageRequest);
                break;

            case CURRENT:
                bookings = bookingRepository
                        .getByBookerIdAndStartBeforeAndEndAfterOrderByStartDesc(
                        bookerId, LocalDateTime.now(), LocalDateTime.now(), pageRequest);
                break;

            case PAST:
                bookings = bookingRepository
                        .getByBookerIdAndEndBeforeOrderByStartDesc(bookerId, LocalDateTime.now(), pageRequest);
                break;

            case FUTURE:
                bookings = bookingRepository
                        .getByBookerIdAndStartAfterOrderByStartDesc(bookerId, LocalDateTime.now(), pageRequest);
                break;

            case WAITING:
                bookings = bookingRepository.getByBookerIdAndStatusEqualsOrderByStartDesc(
                        bookerId, BookingStatus.WAITING, pageRequest);
                break;

            case REJECTED:
                bookings = bookingRepository.getByBookerIdAndStatusEqualsOrderByStartDesc(
                        bookerId, BookingStatus.REJECTED, pageRequest);
                break;
        }

        for (Booking booking : bookings) {
            bookingDtoList.add(bookingMapper.toBookingDto(booking));
        }

        return bookingDtoList;
    }

    @Override
    public List<BookingResponseDto> getListBookingByOwnerIdAndState(Long ownerId, String stateString, Integer from, Integer size) {
        log.info("BookingService: getListBookingByOwnerIdAndState(): start with ownerId={} " +
                "and state='{}'", ownerId, stateString);
        Pageable pageRequest = PageRequest.of(from / size, size);
        BookingCurrentState state = BookingCurrentState.from(stateString);
        List<Booking> bookings = new ArrayList<>();
        List<BookingResponseDto> bookingDtoList = new ArrayList<>();

        if (!userRepository.existsById(ownerId)) {
            throw new NotFoundException("owner with id=" + ownerId + " not found");
        }

        switch (state) {
            case ALL:
                bookings = bookingRepository.getByItemOwnerIdOrderByStartDesc(ownerId, pageRequest);
                break;

            case CURRENT:
                bookings = bookingRepository.getByItemOwnerIdAndStartBeforeAndEndAfterOrderByStartDesc(
                        ownerId, LocalDateTime.now(), LocalDateTime.now(), pageRequest);
                break;

            case PAST:
                bookings = bookingRepository.getByItemOwnerIdAndEndBeforeOrderByStartDesc(
                        ownerId, LocalDateTime.now(), pageRequest);
                break;

            case FUTURE:
                bookings = bookingRepository.getByItemOwnerIdAndStartAfterOrderByStartDesc(
                        ownerId, LocalDateTime.now(), pageRequest);
                break;

            case WAITING:
                bookings = bookingRepository.getByItemOwnerIdAndStatusEqualsOrderByStartDesc(
                        ownerId, BookingStatus.WAITING, pageRequest);
                break;

            case REJECTED:
                bookings = bookingRepository.getByItemOwnerIdAndStatusEqualsOrderByStartDesc(
                        ownerId, BookingStatus.REJECTED, pageRequest);
                break;
        }

        for (Booking booking : bookings) {
            bookingDtoList.add(bookingMapper.toBookingDto(booking));
        }

        return bookingDtoList;
    }

    private boolean isNotValidStartEndTime(Booking booking) {
        LocalDateTime start = booking.getStart();
        LocalDateTime end = booking.getEnd();

        return start.equals(end) || end.isBefore(start);
    }
}