package com.project.rentAndShareApp.item.dto;

import lombok.Getter;

@Getter
public class ShortCommentDto {
    String text;

    public ShortCommentDto(String text) {
        this.text = text;
    }
}