package com.project.item.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShortItemDto {
    private Long id;
    private String name;

    public ShortItemDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}