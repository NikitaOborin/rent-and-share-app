package com.project.rentAndShareApp.item.dto;

import lombok.Getter;

@Getter
public class ItemResponseDto {
    Long id;
    String name;
    String description;
    Boolean available;

    public ItemResponseDto(Long id, String name, String description, Boolean available) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.available = available;
    }
}