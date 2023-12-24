package com.project.item.service;

import com.project.item.dto.*;

import java.util.List;

public interface ItemService {
    List<ItemWithBookingCommentInfoDto> getListItemsByOwnerId(Long ownerId);

    ItemResponseDto addItem(ItemRequestDto itemDto, Long ownerId);

    ItemResponseDto updateItem(ItemRequestDto itemDto, Long itemId, Long ownerId);

    ItemWithBookingCommentInfoDto getItemById(Long itemId, Long userId);

    List<ItemResponseDto> searchAvailableItemsBySubstring(String substring);

    CommentResponseDto addComment(CommentRequestDto commentDto, Long itemId, Long userId);
}