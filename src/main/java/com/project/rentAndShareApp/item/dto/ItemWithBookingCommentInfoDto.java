package com.project.rentAndShareApp.item.dto;

import com.project.rentAndShareApp.booking.dto.ShortBookingDto;
import lombok.Getter;

import java.util.List;

@Getter
public class ItemWithBookingCommentInfoDto {
    private final Long id;
    private final String name;
    private final String description;
    private final Boolean available;
    private final ShortBookingDto lastBooking;
    private final ShortBookingDto nextBooking;
    private final List<CommentResponseDto> comments;

    public ItemWithBookingCommentInfoDto(Long id,
                                         String name,
                                         String description,
                                         Boolean available,
                                         ShortBookingDto lastBooking,
                                         ShortBookingDto nextBooking,
                                         List<CommentResponseDto> comments) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.available = available;
        this.lastBooking = lastBooking;
        this.nextBooking = nextBooking;
        this.comments = comments;
    }
}