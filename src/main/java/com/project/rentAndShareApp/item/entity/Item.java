package com.project.rentAndShareApp.item.entity;

import com.project.rentAndShareApp.request.entity.ItemRequest;
import com.project.rentAndShareApp.user.entity.User;
import lombok.Data;

@Data
public class Item {
    private Long id;
    private String name;
    private String description;
    private Boolean isAvailable;
    private User owner;
    private ItemRequest itemRequest;

    public Item() {

    }

    public Item(Long id, String name, String description, Boolean isAvailable, User owner, ItemRequest itemRequest) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.isAvailable = isAvailable;
        this.owner = owner;
        this.itemRequest = itemRequest;
    }
}
