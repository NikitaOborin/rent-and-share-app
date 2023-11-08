package com.project.rentAndShareApp.item.dto;

import lombok.Value;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Value
public class ItemRequestDto {
    @NotBlank(message = "field 'name' should not be blank")
    String name;

    @NotBlank(message = "field 'description' should not be blank")
    String description;

    @NotNull(message = "field 'available' should not be null")
    Boolean available;
}