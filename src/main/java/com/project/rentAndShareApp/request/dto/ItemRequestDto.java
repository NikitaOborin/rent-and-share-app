package com.project.rentAndShareApp.request.dto;

import com.project.rentAndShareApp.user.entity.User;
import lombok.Value;

import java.time.LocalDateTime;

@Value
public class ItemRequestDto {
    Long id;
    String description;
    User requester;
    LocalDateTime created;
}
