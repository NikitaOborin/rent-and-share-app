package com.project.rentAndShareApp.item.service;

import com.project.rentAndShareApp.item.dto.ItemWithBookingInfoDto;
import com.project.rentAndShareApp.item.entity.Item;

import java.util.List;

public interface ItemService {
    List<ItemWithBookingInfoDto> getListItemsByOwnerId(Long ownerId);

    Item addItem(Item item);

    Item updateItem(Item item);

    ItemWithBookingInfoDto getItemByIdAndUserId(Long itemId, Long userId);

    List<Item> searchAvailableItemBySubstring(String substring);
}
