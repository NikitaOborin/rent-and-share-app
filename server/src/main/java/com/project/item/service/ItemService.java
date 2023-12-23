package com.project.item.service;

import com.project.item.dto.*;

import java.util.List;

public interface ItemService {
    List<ItemWithBookingCommentInfoDto> getListItemsByOwnerId(Long ownerId);

    ItemResponseDto addItem(ItemRequestDto itemDto, Long userId);

    ItemResponseDto updateItem(ItemRequestDto itemDto, Long itemId, Long userId);

    ItemWithBookingCommentInfoDto getItemByIdAndUserId(Long itemId, Long userId);

    List<ItemResponseDto> searchAvailableItemBySubstring(String substring);

    CommentResponseDto addComment(CommentRequestDto commentDto, Long itemId, Long userId);
}