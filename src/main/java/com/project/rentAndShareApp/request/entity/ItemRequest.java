package com.project.rentAndShareApp.request.entity;

import com.project.rentAndShareApp.user.entity.User;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ItemRequest {
    private Long id;
    private String description;
    private User requester;
    private LocalDateTime created;

    public ItemRequest() {

    }

    public ItemRequest(Long id, String description, User requester, LocalDateTime created) {
        this.id = id;
        this.description = description;
        this.requester = requester;
        this.created = created;
    }
}