package com.project.item.mapper;

import com.project.item.dto.CommentRequestDto;
import com.project.item.dto.CommentResponseDto;
import com.project.item.entity.Comment;
import com.project.item.entity.Item;
import com.project.user.entity.User;
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

    public Comment toComment(CommentRequestDto commentDto, Item item, User user) {
        Comment comment = new Comment();

        comment.setText(commentDto.getText());
        comment.setItem(item);
        comment.setUser(user);
        comment.setCreated(LocalDateTime.now());

        return comment;
    }
}
