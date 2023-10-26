package com.project.rentAndShareApp.item.mapper;

import com.project.rentAndShareApp.item.dto.ItemDto;
import com.project.rentAndShareApp.item.entity.Item;

public class ItemMapper {
    public ItemDto toItemDto(Item item) {
        return new ItemDto(
                item.getId(),
                item.getName(),
                item.getDescription(),
                item.getIsAvailable(),
                item.getOwner(),
                item.getItemRequest() != null ? item.getItemRequest() : null
        );
    }

    public Item toItem(ItemDto itemDto) {
        return new Item(
                itemDto.getId(),
                itemDto.getName(),
                itemDto.getDescription(),
                itemDto.getIsAvailable(),
                itemDto.getOwner(),
                itemDto.getItemRequest() != null ? itemDto.getItemRequest() : null
        );
    }
}
