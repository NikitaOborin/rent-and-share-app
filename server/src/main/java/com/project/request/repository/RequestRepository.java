package com.project.request.repository;

import com.project.request.entity.Request;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RequestRepository extends JpaRepository<Request, Long> {
    List<Request> getByRequesterIdOrderByCreatedDesc(Long requesterId);

    List<Request> getByRequesterIdNot(Long requesterId, Pageable pageable);
}