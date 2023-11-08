package com.project.rentAndShareApp.booking.mapper;

import com.project.rentAndShareApp.booking.dto.BookingRequestDto;
import com.project.rentAndShareApp.booking.dto.BookingResponseDto;
import com.project.rentAndShareApp.booking.entity.Booking;
import com.project.rentAndShareApp.item.entity.Item;
import com.project.rentAndShareApp.user.entity.User;
import org.springframework.stereotype.Component;

@Component
public class BookingMapper {
    public BookingResponseDto toBookingDto(Booking booking) {
        return new BookingResponseDto(
                booking.getId(),
                booking.getStart(),
                booking.getEnd(),
                booking.getStatus(),
                new BookingResponseDto.ShortUserDto(booking.getBooker().getId()),
                new BookingResponseDto.ShortItemDto(booking.getItem().getId(), booking.getItem().getName())
        );
    }

    public Booking toBooking(BookingRequestDto bookingDto, Long userId) {
        Booking booking = new Booking();

        Item item = new Item();
        item.setId(bookingDto.getItemId());

        User booker = new User();
        booker.setId(userId);

        booking.setStart(bookingDto.getStart());
        booking.setEnd(bookingDto.getEnd());
        booking.setItem(item);
        booking.setBooker(booker);

        return booking;
    }
}