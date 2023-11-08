package com.project.rentAndShareApp.user.dto;

import lombok.Value;

@Value
public class UserResponseDto {
    Long id;
    String name;
    String email;
}