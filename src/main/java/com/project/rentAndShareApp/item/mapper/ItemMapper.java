package com.project.rentAndShareApp.item.mapper;

import com.project.rentAndShareApp.booking.dto.ShortBookingDto;
import com.project.rentAndShareApp.item.dto.*;
import com.project.rentAndShareApp.item.entity.Item;
import com.project.rentAndShareApp.user.entity.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ItemMapper {
    public ItemResponseDto toItemDto(Item item) {
        return new ItemResponseDto(
                item.getId(),
                item.getName(),
                item.getDescription(),
                item.getAvailable()
        );
    }

    public ItemWithBookingCommentInfoDto toItemWithBookingCommentInfoDto(Item item,
                                                                         ShortBookingDto lastBooking,
                                                                         ShortBookingDto nextBooking,
                                                                         List<CommentResponseDto> comments) {
        return new ItemWithBookingCommentInfoDto(
                item.getId(),
                item.getName(),
                item.getDescription(),
                item.getAvailable(),
                lastBooking,
                nextBooking,
                comments
        );
    }

    public ItemWithRequestInfoDto toItemWithRequestInfoDto(Item item) {
        return new ItemWithRequestInfoDto(
                item.getId(),
                item.getName(),
                item.getDescription(),
                item.getAvailable(),
                item.getRequest().getId()
        );
    }

    public Item toItem(ItemRequestDto itemDto, Long itemId, Long userId) {
        Item item = new Item();

        User owner = new User();
        owner.setId(userId);

        item.setId(itemId);
        item.setName(itemDto.getName());
        item.setDescription(itemDto.getDescription());
        item.setAvailable(itemDto.getAvailable());
        item.setOwner(owner);

        return item;
    }

    public Item toItem(ItemRequestDto itemDto, Long userId) {
        Item item = new Item();

        User owner = new User();
        owner.setId(userId);

        item.setName(itemDto.getName());
        item.setDescription(itemDto.getDescription());
        item.setAvailable(itemDto.getAvailable());
        item.setOwner(owner);

        return item;
    }
}
