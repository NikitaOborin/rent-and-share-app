package com.project.rentAndShareApp.item.mapper;

import com.project.rentAndShareApp.item.dto.CommentRequestDto;
import com.project.rentAndShareApp.item.dto.CommentResponseDto;
import com.project.rentAndShareApp.item.dto.ShortCommentDto;
import com.project.rentAndShareApp.item.entity.Comment;
import com.project.rentAndShareApp.item.entity.Item;
import com.project.rentAndShareApp.user.entity.User;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class CommentMapper {
    public CommentResponseDto toCommentDto(Comment comment) {
        return new CommentResponseDto(
                comment.getId(),
                comment.getText(),
                comment.getUser().getName(),
                comment.getCreated()
        );
    }

    public ShortCommentDto toShortCommentDto() {

    }

    public Comment toComment(CommentRequestDto commentDto, Item item, User user) {
        Comment comment = new Comment();

        comment.setText(commentDto.getText());
        comment.setItem(item);
        comment.setUser(user);
        comment.setCreated(LocalDateTime.now());

        return comment;
    }
}
