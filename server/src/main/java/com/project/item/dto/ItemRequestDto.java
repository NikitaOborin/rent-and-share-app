package com.project.item.dto;

import lombok.Getter;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Setter;

@Getter
@Setter
public class ItemRequestDto {
    @NotBlank(message = "field 'name' should not be blank")
    private String name;

    @NotBlank(message = "field 'description' should not be blank")
    private String description;

    @NotNull(message = "field 'available' should not be null")
    private Boolean available;

    private Long requestId;

    public ItemRequestDto(String name, String description, Boolean available, Long requestId) {
        this.name = name;
        this.description = description;
        this.available = available;
        this.requestId = requestId;
    }
}