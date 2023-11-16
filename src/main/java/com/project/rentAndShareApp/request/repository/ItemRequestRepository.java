package com.project.rentAndShareApp.request.repository;

import com.project.rentAndShareApp.request.entity.ItemRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRequestRepository extends JpaRepository<ItemRequest, Long> {
}
