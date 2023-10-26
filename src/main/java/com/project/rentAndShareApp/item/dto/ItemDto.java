package com.project.rentAndShareApp.item.dto;

import com.project.rentAndShareApp.request.entity.ItemRequest;
import com.project.rentAndShareApp.user.entity.User;
import lombok.Value;

@Value
public class ItemDto {
    Long id;
    String name;
    String description;
    Boolean isAvailable;
    User owner;
    ItemRequest itemRequest;
}
