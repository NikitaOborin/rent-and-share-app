package com.project.rentAndShareApp.item.dto;

import com.project.rentAndShareApp.request.entity.ItemRequest;
import com.project.rentAndShareApp.user.entity.User;
import lombok.Value;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Value
public class ItemDto {
    Long id;

    @NotBlank(message = "поле name не может быть пустым")
    String name;

    @NotBlank(message = "поле description не может быть пустым")
    String description;

    @NotNull(message = "поле available не может быть null")
    Boolean available;

    ItemRequest itemRequest;
}
