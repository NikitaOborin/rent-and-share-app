package com.project.rentAndShareApp.booking.mapper;

import com.project.rentAndShareApp.booking.dto.BookingBookerDto;
import com.project.rentAndShareApp.booking.dto.BookingDto;
import com.project.rentAndShareApp.booking.dto.BookingItemDto;
import com.project.rentAndShareApp.booking.entity.Booking;
import com.project.rentAndShareApp.item.entity.Item;
import com.project.rentAndShareApp.user.entity.User;
import org.springframework.stereotype.Component;

@Component
public class BookingMapper {
    public BookingDto toBookingDto(Booking booking) {
        BookingItemDto bookingItemDto = new BookingItemDto(booking.getItem().getId(), booking.getItem().getName());
        BookingBookerDto bookingBookerDto = new BookingBookerDto(booking.getBooker().getId());

        return new BookingDto(
                booking.getId(),
                booking.getItem().getId(),
                booking.getStart(),
                booking.getEnd(),
                bookingItemDto,
                bookingBookerDto,
                booking.getStatus()
        );
    }

    public Booking toBooking(BookingDto bookingDto, Long userId) {
        Item item = new Item();
        User booker = new User();

        item.setId(bookingDto.getItemId());
        booker.setId(userId);

         return new Booking(
                bookingDto.getId(),
                bookingDto.getStart(),
                bookingDto.getEnd(),
                 item,
                booker,
                bookingDto.getStatus()
        );
    }
}