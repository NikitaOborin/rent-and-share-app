package com.project.rentAndShareApp.item.repository;

import com.project.rentAndShareApp.item.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    List<Item> getItemsByOwnerId(Long ownerId);

    List<Item> getByNameIgnoreCaseOrDescriptionIgnoreCaseContainingAndAvailableIsTrueOrderById(String s1, String s2);

    List<Item> getByRequestId(Long requestId);
}