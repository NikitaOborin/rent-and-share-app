package com.project.rentAndShareApp.booking.dto;

import com.project.rentAndShareApp.booking.entity.BookingStatus;
import com.project.rentAndShareApp.item.dto.ShortItemDto;
import com.project.rentAndShareApp.user.dto.ShortUserDto;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class BookingResponseDto {
    private Long id;
    private LocalDateTime start;
    private LocalDateTime end;
    private BookingStatus status;
    private ShortUserDto booker;
    private ShortItemDto item;

    public BookingResponseDto(Long id,
                              LocalDateTime start,
                              LocalDateTime end,
                              BookingStatus status,
                              ShortUserDto booker,
                              ShortItemDto item) {
        this.id = id;
        this.start = start;
        this.end = end;
        this.status = status;
        this.booker = booker;
        this.item = item;
    }
}