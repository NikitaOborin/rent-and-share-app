package com.project.rentAndShareApp.item.repository;

import com.project.rentAndShareApp.item.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
