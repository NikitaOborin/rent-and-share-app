package com.project.rentAndShareApp.booking.dto;

import lombok.Getter;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
public class BookingRequestDto {
    @NotNull(message = "field 'itemId' should not be null")
    Long itemId;

    @NotNull(message = "field 'start' should not be null")
    @FutureOrPresent(message = "field 'start' should be in the present or in the future")
    LocalDateTime start;

    @NotNull(message = "field 'end' should not be null")
    @FutureOrPresent(message = "field 'end' should be in the present or in the future")
    LocalDateTime end;
}