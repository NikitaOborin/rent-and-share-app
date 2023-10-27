package com.project.rentAndShareApp.user.dto;

import lombok.Value;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Value
public class UserDto {
    Long id;

    @NotBlank
    String name;

    @Email
    String email;
}
