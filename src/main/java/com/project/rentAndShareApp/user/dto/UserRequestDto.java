package com.project.rentAndShareApp.user.dto;

import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Getter
public class UserRequestDto {
    String name;

    @NotNull(message = "field 'email' should not be null")
    @Email(message = "field 'email' not correct")
    String email;
}