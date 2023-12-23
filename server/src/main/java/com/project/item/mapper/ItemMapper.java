package com.project.item.mapper;

import com.project.booking.dto.ShortBookingDto;
import com.project.item.dto.*;
import com.project.item.entity.Item;
import com.project.request.entity.Request;
import com.project.user.entity.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ItemMapper {
    public ItemResponseDto toItemDto(Item item) {
        Long requestId = null;

        if (item.getRequest() != null) {
            requestId = item.getRequest().getId();
        }

        return new ItemResponseDto(
                item.getId(),
                item.getName(),
                item.getDescription(),
                item.getAvailable(),
                requestId
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

        if (itemDto.getRequestId() != null) {
            Request request = new Request();

            request.setId(itemDto.getRequestId());

            item.setRequest(request);
        }

        return item;
    }
}
