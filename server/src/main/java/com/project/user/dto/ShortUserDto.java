package com.project.user.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShortUserDto {
    private Long id;

    public ShortUserDto(Long id) {
        this.id = id;
    }
}

