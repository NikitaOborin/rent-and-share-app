package com.project.item.controller;

import com.project.item.dto.*;
import com.project.item.service.ItemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@Slf4j
@Validated
@RestController
@RequestMapping("items")
public class ItemController {
    private final ItemService itemService;

    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping
    public List<ItemWithBookingCommentInfoDto> getListItemsByOwnerId(@RequestHeader("X-Sharer-User-Id") Long ownerId) {
        log.info("ItemController: getListItemsForUserId(): start with ownerId={}", ownerId);
        return itemService.getListItemsByOwnerId(ownerId);
    }

    @PostMapping
    public ItemResponseDto addItem(@RequestBody @Valid ItemRequestDto itemDto,
                                   @RequestHeader("X-Sharer-User-Id") Long ownerId) {
        log.info("ItemController: addItem(): start with ownerId={} and itemDto='{}'", ownerId, itemDto);
        return itemService.addItem(itemDto, ownerId);
    }

    @PatchMapping("{itemId}")
    public ItemResponseDto updateItem(@RequestBody ItemRequestDto itemDto,
                                      @PathVariable Long itemId,
                                      @RequestHeader("X-Sharer-User-Id") Long ownerId) {
        log.info("ItemController: updateItem(): start with ownerId={} and itemId={}", ownerId, itemId);
        return itemService.updateItem(itemDto, itemId, ownerId);
    }

    @GetMapping("{itemId}")
    public ItemWithBookingCommentInfoDto getItemById(@PathVariable Long itemId,
                                                     @RequestHeader("X-Sharer-User-Id") Long userId) {
        log.info("ItemController: getItemById(): start with itemId={} and with userId={}", itemId, userId);
        return itemService.getItemById(itemId, userId);
    }

    @GetMapping("search")
    public List<ItemResponseDto> searchAvailableItemsBySubstring(@RequestParam String text) {
        log.info("ItemController: searchAvailableItemsBySubstring(): start with substring='{}'", text);
        return itemService.searchAvailableItemsBySubstring(text);
    }

    @PostMapping("{itemId}/comment")
    public CommentResponseDto addComment(@RequestBody @Valid CommentRequestDto commentDto,
                                         @PathVariable Long itemId,
                                         @RequestHeader("X-Sharer-User-Id") Long userId) {
        log.info("ItemController: addComment(): start with commentDto='{}', itemId={} and userId={}",
                commentDto, itemId, userId);
        return itemService.addComment(commentDto, itemId, userId);
    }
}