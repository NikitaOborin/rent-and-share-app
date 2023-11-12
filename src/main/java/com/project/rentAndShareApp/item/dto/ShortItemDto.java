package com.project.rentAndShareApp.item.dto;

import lombok.Getter;

@Getter
public class ShortItemDto {
    Long id;
    String name;

    public ShortItemDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}