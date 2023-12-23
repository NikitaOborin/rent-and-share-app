package com.project.item.entity;

import com.project.user.entity.User;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "comment")
@Getter
@Setter
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;

    private String text;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "time_created")
    private LocalDateTime created;

    public Comment() {
    }

    public Comment(Long id, String text, Item item, User user, LocalDateTime created) {
        this.id = id;
        this.text = text;
        this.item = item;
        this.user = user;
        this.created = created;
    }
}