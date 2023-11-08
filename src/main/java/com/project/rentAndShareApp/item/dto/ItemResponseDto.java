package com.project.rentAndShareApp.item.dto;

import lombok.Value;

@Value
public class ItemResponseDto {
    Long id;
    String name;
    String description;
    Boolean available;
}