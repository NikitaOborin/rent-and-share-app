package com.project.rentAndShareApp.item.repository;

import com.project.rentAndShareApp.exception.NotFoundException;
import com.project.rentAndShareApp.item.entity.Item;
import com.project.rentAndShareApp.user.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Repository
public class ItemInMemoryRepository implements ItemRepository {
    private final Map<Long, Item> items = new HashMap<>();
    private Long counter = 1L;
    @Override
    public List<Item> getListItemsForUserId(Long userId) {
        List<Item> userItems = new ArrayList<>();

        for (Item item : items.values().stream().toList()) {
            if (item.getOwner().getId().equals(userId)) {
                userItems.add(item);
            }
        }

        return userItems;
    }

    @Override
    public Item addItem(Item item) {
        item.setId(counter);

        items.put(counter, item);

        counter++;

        return  item;
    }

    @Override
    public Item updateItem(Item item, Long itemId, User user) {
        if (!items.containsKey(itemId)) {
            throw new NotFoundException("item with id = " + itemId + " not found");
        }

        if (!items.get(itemId).getOwner().getId().equals(user.getId())) {
            throw new NotFoundException("user with id = " + user.getId() + " not owner for item with id = " + itemId);
        }

        Item itemInDb = items.get(itemId);

        if (item.getName() != null) {
            itemInDb.setName(item.getName());
        }

        if (item.getDescription() != null) {
            itemInDb.setDescription(item.getDescription());
        }

        if (item.getAvailable() != null) {
            itemInDb.setAvailable(item.getAvailable());
        }

        return itemInDb;
    }

    @Override
    public Item getItemById(Long itemId) {
        if (!items.containsKey(itemId)) {
            throw new NotFoundException("item with id = " + itemId + " not found");
        }

        return items.get(itemId);
    }

    @Override
    public List<Item> searchAvailableItemBySubstring(String substring) {
        List<Item> foundItems = new ArrayList<>();

        for (Item currentItem : items.values()) {
            String itemNameLowerCase = currentItem.getName().toLowerCase();
            String itemDescriptionLowerCase = currentItem.getDescription().toLowerCase();

            if ((itemNameLowerCase.contains(substring.toLowerCase())
                    || itemDescriptionLowerCase.contains(substring.toLowerCase()))
                    && currentItem.getAvailable()) {
                foundItems.add(currentItem);
            }
        }

        return foundItems;
    }
}
