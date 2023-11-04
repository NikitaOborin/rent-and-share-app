package com.project.rentAndShareApp.item.service;

import com.project.rentAndShareApp.item.entity.Item;
import com.project.rentAndShareApp.item.repository.ItemRepository;
import com.project.rentAndShareApp.user.entity.User;
import com.project.rentAndShareApp.user.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
        return null;
    }

    @Override
    public Item addItem(Item item, Long userId) {
        return null;
    }

    @Override
    public Item updateItem(Item item, Long itemId, Long userId) {
        return null;
    }

    @Override
    public Item getItemById(Long itemId) {
        return null;
    }

    @Override
    public List<Item> searchAvailableItemBySubstring(String substring) {
        return null;
    }
}
