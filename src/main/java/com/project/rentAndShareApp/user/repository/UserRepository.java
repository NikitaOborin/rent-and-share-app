package com.project.rentAndShareApp.user.repository;

import com.project.rentAndShareApp.user.entity.User;

import java.util.List;

public interface UserRepository {
    List<User> getListUsers();

    User addUser(User user);

    User updateUser(User user);

    User getUserById(Long userId);

    void deleteUserById(Long userId);
}
