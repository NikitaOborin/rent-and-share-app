package com.project.rentAndShareApp.item.repository;

import com.project.rentAndShareApp.item.entity.Item;
import com.project.rentAndShareApp.user.entity.User;

import java.util.List;

public interface ItemRepository {
    List<Item> getListItemsForUserId(Long userId);

    Item addItem(Item item);

    Item updateItem(Item item, Long itemId, User user);

    Item getItemById(Long itemId);

    List<Item> searchAvailableItemBySubstring(String substring);
}
