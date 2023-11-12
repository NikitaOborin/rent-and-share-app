package com.project.rentAndShareApp.item.repository;

import com.project.rentAndShareApp.item.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {
    List<Item> getItemsByOwnerId(Long ownerId);

    List<Item> getByNameIgnoreCaseOrDescriptionIgnoreCaseContainingAndAvailableIsTrueOrderById(String s1, String s2);

    Boolean existsByIdAndAvailableTrue(Long itemId);
}