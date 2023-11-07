package com.project.rentAndShareApp.item.service;

import com.project.rentAndShareApp.exception.NotFoundException;
import com.project.rentAndShareApp.item.entity.Item;
import com.project.rentAndShareApp.item.repository.ItemRepository;
import com.project.rentAndShareApp.user.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class ItemServiceImpl implements ItemService {
    private final ItemRepository itemRepository;
    private final UserRepository userRepository;

    @Autowired
    public ItemServiceImpl(ItemRepository itemRepository, UserRepository userRepository) {
        this.itemRepository = itemRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<Item> getListItemsForUserId(Long userId) {
        log.info("ItemService: getListItemsForUserId(): start with userId={}", userId);
        return itemRepository.getItemsByOwnerId(userId);
    }

    @Override
    public Item addItem(Item item, Long userId) {
        log.info("ItemService: addItem(): start with userId = {} and item: '{}'", userId, item);
        if (!userRepository.existsById(userId)) {
            throw new NotFoundException("user with id=" + userId + " not found");
        }

        item.setOwner(userRepository.getReferenceById(userId));

        return itemRepository.save(item);
    }

    @Override
    public Item updateItem(Item item, Long itemId, Long userId) {
        log.info("ItemService: updateItem(): start with userId = {} and itemId = {}", userId, itemId);
        if (!itemRepository.existsById(itemId)) {
            throw new NotFoundException("item with id=" + itemId + " not found");
        }

        Item existItem = itemRepository.getReferenceById(itemId);

        if (!existItem.getOwner().getId().equals(userId)) {
            throw new NotFoundException("user with id=" + userId + "not owner for item with id=" + itemId);
        }

        if (item.getName() != null) {
            existItem.setName(item.getName());
        }

        if (item.getDescription() != null) {
            existItem.setDescription(item.getDescription());
        }

        if (item.getAvailable() != null) {
            existItem.setAvailable(item.getAvailable());
        }

        return itemRepository.save(existItem);
    }

    @Override
    public Item getItemById(Long itemId) {
        log.info("ItemService: getItemById(): start with itemId = {}", itemId);
        Optional<Item> optionalItem = itemRepository.findById(itemId);

        if (optionalItem.isEmpty()) {
            throw new NotFoundException("item with id=" + itemId + " not found");
        }

        return optionalItem.get();
    }

    @Override
    public List<Item> searchAvailableItemBySubstring(String substring) {
        log.info("ItemService: getItemById(): start with substring = '{}'", substring);
        List<Item> items = new ArrayList<>();
        String s = substring.toUpperCase();

        if (!substring.isBlank()) {
            items = itemRepository.getByNameIgnoreCaseOrDescriptionIgnoreCaseContainingAndAvailableIsTrueOrderById(s, s);
        }

        return items;
    }
}