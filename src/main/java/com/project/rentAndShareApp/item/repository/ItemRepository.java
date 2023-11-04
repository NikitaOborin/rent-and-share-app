package com.project.rentAndShareApp.item.repository;

import com.project.rentAndShareApp.item.entity.Item;
import com.project.rentAndShareApp.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {

}
