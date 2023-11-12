package com.project.rentAndShareApp.item.service;

import com.project.rentAndShareApp.item.dto.ItemRequestDto;
import com.project.rentAndShareApp.item.dto.ItemResponseDto;
import com.project.rentAndShareApp.item.dto.ItemWithBookingInfoDto;

import java.util.List;

public interface ItemService {
    List<ItemWithBookingInfoDto> getListItemsByOwnerId(Long ownerId);

    ItemResponseDto addItem(ItemRequestDto itemDto, Long userId);

    ItemResponseDto updateItem(ItemRequestDto itemDto, Long itemId, Long userId);

    ItemWithBookingInfoDto getItemByIdAndUserId(Long itemId, Long userId);

    List<ItemResponseDto> searchAvailableItemBySubstring(String substring);
}
