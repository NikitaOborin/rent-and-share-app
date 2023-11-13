package com.project.rentAndShareApp.item.dto;

import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class CommentRequestDto {
    @NotBlank(message = "field 'text' should not be blank")
    String text;
}
