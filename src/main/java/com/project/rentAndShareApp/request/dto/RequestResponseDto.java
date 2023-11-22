package com.project.rentAndShareApp.request.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class RequestResponseDto {
    private Long id;
    private String description;
    private LocalDateTime created;

    public RequestResponseDto() {
    }

    public RequestResponseDto(Long id, String description, LocalDateTime created) {
        this.id = id;
        this.description = description;
        this.created = created;
    }
}