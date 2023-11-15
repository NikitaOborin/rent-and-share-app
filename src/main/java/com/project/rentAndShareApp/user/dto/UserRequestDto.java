package com.project.rentAndShareApp.user.dto;

import lombok.Getter;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Setter;

@Getter
@Setter
public class UserRequestDto {
    private String name;

    @NotNull(message = "field 'email' should not be null")
    @Email(message = "field 'email' not correct")
    private String email;

    public UserRequestDto() {
    }

    public UserRequestDto(String name, String email) {
        this.name = name;
        this.email = email;
    }
}