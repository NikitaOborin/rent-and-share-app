package com.project.rentAndShareApp.user.entity;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class User {
    private Long id;
    private String name;

    @NotNull(message = "поле email не может быть null")
    @Email(message = "некорректный email")
    private String email;

    public User() {
    }

    public User(Long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }
}
