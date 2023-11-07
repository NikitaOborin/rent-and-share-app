package com.project.rentAndShareApp.item.controller;

import com.project.rentAndShareApp.item.dto.ItemDto;
import com.project.rentAndShareApp.item.entity.Item;
import com.project.rentAndShareApp.item.mapper.ItemMapper;
import com.project.rentAndShareApp.item.service.ItemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Validated
@RestController
@RequestMapping("/items")
public class ItemController {
    private final ItemService itemService;
    private final ItemMapper itemMapper;

    @Autowired
    public ItemController(ItemService itemService, ItemMapper itemMapper) {
        this.itemService = itemService;
        this.itemMapper = itemMapper;
    }

    @GetMapping()
    public List<ItemDto> getListItemsForUserId(@RequestHeader("X-Sharer-User-Id") Long userId) {
        log.info("ItemController: getListItemsForUserId(): start with userId={}", userId);
        List<ItemDto> itemDtoList = new ArrayList<>();

        for (Item item : itemService.getListItemsForUserId(userId)) {
            itemDtoList.add(itemMapper.toItemDto(item));
        }

        return itemDtoList;
    }

    @PostMapping()
    public ItemDto addItem(@RequestBody @Valid ItemDto itemDto,
                           @RequestHeader("X-Sharer-User-Id") Long userId) {
        log.info("ItemController: addItem(): start with userId = {} and itemDto: '{}'", userId, itemDto);
        Item addedItem = itemService.addItem(itemMapper.toItem(itemDto, userId), userId);

        return itemMapper.toItemDto(addedItem);
    }

    @PatchMapping("/{itemId}")
    public ItemDto updateItem(@RequestBody ItemDto itemDto,
                              @PathVariable Long itemId,
                              @RequestHeader("X-Sharer-User-Id") Long userId) {
        log.info("ItemController: updateItem(): start with userId = {} and itemId = {}", userId, itemId);
        Item updatedItem = itemService.updateItem(itemMapper.toItem(itemDto, userId), itemId, userId);

        return itemMapper.toItemDto(updatedItem);
    }

    @GetMapping("/{itemId}")
    public ItemDto getItemById(@PathVariable Long itemId) {
        log.info("ItemController: getItemById(): start with itemId = {}", itemId);
        Item item = itemService.getItemById(itemId);

        return itemMapper.toItemDto(item);
    }

    @GetMapping("/search")
    public List<ItemDto> searchAvailableItemBySubstring(@RequestParam String text) {
        log.info("ItemController: getItemById(): start with substring = '{}'", text);
        List<ItemDto> itemDtoList = new ArrayList<>();

        for (Item item : itemService.searchAvailableItemBySubstring(text)) {
            itemDtoList.add(itemMapper.toItemDto(item));
        }

        return itemDtoList;
    }
}
