package com.project.rentAndShareApp.item.controller;

import com.project.rentAndShareApp.item.dto.ItemRequestDto;
import com.project.rentAndShareApp.item.dto.ItemResponseDto;
import com.project.rentAndShareApp.item.dto.ItemWithBookingInfoDto;
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
    public List<ItemWithBookingInfoDto> getListItemsByOwnerId(@RequestHeader("X-Sharer-User-Id") Long ownerId) {
        log.info("ItemController: getListItemsForUserId(): start with ownerId={}", ownerId);
        return itemService.getListItemsByOwnerId(ownerId);
    }

    @PostMapping()
    public ItemResponseDto addItem(@RequestBody @Valid ItemRequestDto itemDto,
                                   @RequestHeader("X-Sharer-User-Id") Long userId) {
        log.info("ItemController: addItem(): start with userId = {} and itemDto: '{}'", userId, itemDto);
        Item addedItem = itemService.addItem(itemMapper.toItem(itemDto, userId));

        return itemMapper.toItemDto(addedItem);
    }

    @PatchMapping("/{itemId}")
    public ItemResponseDto updateItem(@RequestBody ItemRequestDto itemDto,
                                      @PathVariable Long itemId,
                                      @RequestHeader("X-Sharer-User-Id") Long userId) {
        log.info("ItemController: updateItem(): start with userId = {} and itemId = {}", userId, itemId);
        Item updatedItem = itemService.updateItem(itemMapper.toItem(itemDto, itemId, userId));

        return itemMapper.toItemDto(updatedItem);
    }

    @GetMapping("/{itemId}")
    public ItemWithBookingInfoDto getItemById(@PathVariable Long itemId,
                                              @RequestHeader(value = "X-Sharer-User-Id", required = false) Long userId) {
        log.info("ItemController: getItemById(): start with itemId = {} and with userId={}", itemId, userId);
        return itemService.getItemByIdAndUserId(itemId, userId);
    }

    @GetMapping("/search")
    public List<ItemResponseDto> searchAvailableItemBySubstring(@RequestParam String text) {
        log.info("ItemController: getItemById(): start with substring = '{}'", text);
        List<ItemResponseDto> itemDtoList = new ArrayList<>();

        for (Item item : itemService.searchAvailableItemBySubstring(text)) {
            itemDtoList.add(itemMapper.toItemDto(item));
        }

        return itemDtoList;
    }


}