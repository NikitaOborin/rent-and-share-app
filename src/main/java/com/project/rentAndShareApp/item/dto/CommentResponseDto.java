package com.project.rentAndShareApp.item.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CommentResponseDto {
    Long id;
    String text;
    String authorName;
    LocalDateTime created;

    public CommentResponseDto(Long id, String text, String authorName, LocalDateTime created) {
        this.id = id;
        this.text = text;
        this.authorName = authorName;
        this.created = created;
    }
}