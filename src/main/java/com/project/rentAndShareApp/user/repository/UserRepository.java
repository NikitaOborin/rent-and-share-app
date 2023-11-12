package com.project.rentAndShareApp.user.repository;

import com.project.rentAndShareApp.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    Boolean existsUserByEmail(String email);
}