package com.project.rentAndShareApp.request.dto;

import com.project.rentAndShareApp.user.entity.User;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ItemRequestDto {
    private Long id;
    private String description;
    private User requester;
    private LocalDateTime created;

    public ItemRequestDto() {
    }

    public ItemRequestDto(Long id, String description, User requester, LocalDateTime created) {
        this.id = id;
        this.description = description;
        this.requester = requester;
        this.created = created;
    }
}