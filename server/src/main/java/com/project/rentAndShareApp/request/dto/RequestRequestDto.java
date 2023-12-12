package com.project.rentAndShareApp.request.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestRequestDto {
    @NotBlank
    private String description;

    public RequestRequestDto() {
    }

    public RequestRequestDto(String description) {
        this.description = description;
    }
}