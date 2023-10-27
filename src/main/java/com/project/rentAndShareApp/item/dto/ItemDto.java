package com.project.rentAndShareApp.item.dto;

import com.project.rentAndShareApp.request.entity.ItemRequest;
import com.project.rentAndShareApp.user.entity.User;
import lombok.Value;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Value
public class ItemDto {
    Long id;

    @NotBlank
    String name;

    String description;
    Boolean isAvailable;
    User owner;
    ItemRequest itemRequest;
}
