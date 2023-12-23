package com.project.booking.dto;

import lombok.Getter;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class BookingRequestDto {
    @NotNull(message = "field 'itemId' should not be null")
    private Long itemId;

    @NotNull(message = "field 'start' should not be null")
    @FutureOrPresent(message = "field 'start' should be in the present or in the future")
    private LocalDateTime start;

    @NotNull(message = "field 'end' should not be null")
    @FutureOrPresent(message = "field 'end' should be in the present or in the future")
    private LocalDateTime end;

    public BookingRequestDto() {
    }

    public BookingRequestDto(Long itemId, LocalDateTime start, LocalDateTime end) {
        this.itemId = itemId;
        this.start = start;
        this.end = end;
    }
}