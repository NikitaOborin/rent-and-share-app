package com.project.rentAndShareApp.item.service;

import com.project.rentAndShareApp.item.entity.Item;

import java.util.List;

public interface ItemService {
    List<Item> getListItemsForUserId(Long userId);

    Item addItem(Item item);

    Item updateItem(Item item);

    Item getItemById(Long itemId);

    List<Item> searchAvailableItemBySubstring(String substring);
}
