package com.project.rentAndShareApp.user.dto;

import lombok.Getter;

@Getter
public class UserResponseDto {
    Long id;
    String name;
    String email;

    public UserResponseDto(Long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }
}