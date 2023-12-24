package com.project.request.dto;

import com.project.item.dto.ItemResponseDto;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class RequestWithItemInfoDto {
    private Long id;
    private String description;
    private LocalDateTime created;
    private List<ItemResponseDto> items;

    public RequestWithItemInfoDto() {
    }

    public RequestWithItemInfoDto(Long id, String description, LocalDateTime created, List<ItemResponseDto> items) {
        this.id = id;
        this.description = description;
        this.created = created;
        this.items = items;
    }
}