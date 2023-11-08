package com.project.rentAndShareApp.booking.dto;

import com.project.rentAndShareApp.booking.entity.BookingStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.Value;

import java.time.LocalDateTime;

@Value
public class BookingResponseDto {
    Long id;
    LocalDateTime start;
    LocalDateTime end;
    BookingStatus status;
    ShortUserDto booker;
    ShortItemDto item;

    @Getter
    @Setter
    public static class ShortUserDto {
        Long id;

        public ShortUserDto(Long id) {
            this.id = id;
        }
    }

    @Getter
    @Setter
    public static class ShortItemDto {
        Long id;
        String name;

        public ShortItemDto(Long id, String name) {
            this.id = id;
            this.name = name;
        }
    }
}