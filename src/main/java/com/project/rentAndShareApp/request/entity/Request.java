package com.project.rentAndShareApp.request.entity;

import com.project.rentAndShareApp.user.entity.User;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "request")
public class Request {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "request_id")
    private Long id;

    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User requester;

    @Column(name = "create_time")
    private LocalDateTime created;

    public Request() {
    }

    public Request(Long id, String description, User requester, LocalDateTime created) {
        this.id = id;
        this.description = description;
        this.requester = requester;
        this.created = created;
    }
}