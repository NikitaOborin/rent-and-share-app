package com.project.rentAndShareApp.user.dto;

import lombok.Getter;

@Getter
public class ShortUserDto {
    Long id;

    public ShortUserDto(Long id) {
        this.id = id;
    }
}

