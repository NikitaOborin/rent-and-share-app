package com.project.rentAndShareApp.item.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemWithRequestInfoDto {
    private Long id;
    private String name;
    private String description;
    private Boolean available;
    private Long requestId;

    public ItemWithRequestInfoDto() {
    }

    public ItemWithRequestInfoDto(Long id, String name, String description, Boolean available, Long requestId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.available = available;
        this.requestId = requestId;
    }
}