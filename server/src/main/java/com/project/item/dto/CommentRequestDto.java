package com.project.item.dto;

import lombok.Getter;

import jakarta.validation.constraints.NotBlank;
import lombok.Setter;

@Getter
@Setter
public class CommentRequestDto {
    @NotBlank(message = "field 'text' should not be blank")
    private String text;

    public CommentRequestDto() {
    }

    public CommentRequestDto(String text) {
        this.text = text;
    }
}