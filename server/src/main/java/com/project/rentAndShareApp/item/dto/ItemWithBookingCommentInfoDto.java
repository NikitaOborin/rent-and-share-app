package com.project.rentAndShareApp.item.dto;

import com.project.rentAndShareApp.booking.dto.ShortBookingDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ItemWithBookingCommentInfoDto {
    private Long id;
    private String name;
    private String description;
    private Boolean available;
    private ShortBookingDto lastBooking;
    private ShortBookingDto nextBooking;
    private List<CommentResponseDto> comments;

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