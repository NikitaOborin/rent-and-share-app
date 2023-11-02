package com.project.rentAndShareApp.item.entity;

import com.project.rentAndShareApp.request.entity.ItemRequest;
import com.project.rentAndShareApp.user.entity.User;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Table(name = "item")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private Long id;

    @NotBlank(message = "поле name не может быть пустым")
    private String name;

    @NotBlank(message = "поле description не может быть пустым")
    private String description;

    @NotNull(message = "поле available не может быть null")
    private Boolean available;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User owner;

    @ManyToOne
    @JoinColumn(name = "request_id")
    private ItemRequest itemRequest;

    public Item() {
    }

    public Item(Long id, String name, String description, Boolean available, User owner, ItemRequest itemRequest) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.available = available;
        this.owner = owner;
        this.itemRequest = itemRequest;
    }
}
