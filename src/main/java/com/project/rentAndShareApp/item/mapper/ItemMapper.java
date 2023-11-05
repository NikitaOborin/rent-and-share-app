package com.project.rentAndShareApp.item.mapper;

import com.project.rentAndShareApp.item.dto.ItemDto;
import com.project.rentAndShareApp.item.entity.Item;
import com.project.rentAndShareApp.user.entity.User;
import org.springframework.stereotype.Component;

@Component
public class ItemMapper {
    public ItemDto toItemDto(Item item) {
        return new ItemDto(
                item.getId(),
                item.getName(),
                item.getDescription(),
                item.getAvailable(),
                item.getItemRequest() != null ? item.getItemRequest() : null
        );
    }

    public Item toItem(ItemDto itemDto, Long userId) {
        User user = new User();

        user.setId(userId);

        return new Item(
                itemDto.getId(),
                itemDto.getName(),
                itemDto.getDescription(),
                itemDto.getAvailable(),
                user,
                itemDto.getItemRequest() != null ? itemDto.getItemRequest() : null
        );
    }
}
