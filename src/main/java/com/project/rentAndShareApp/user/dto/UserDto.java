package com.project.rentAndShareApp.user.dto;

import lombok.Value;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Value
public class UserDto {
    Long id;
    String name;

    @NotNull(message = "поле email не может быть null")
    @Email(message = "некорректный email")
    String email;
}