package com.project.rentAndShareApp.user.service;

import com.project.rentAndShareApp.user.entity.User;

import java.util.List;

public interface UserService {
    List<User> getListUsers();

    User addUser(User user);

    User updateUser(Long userId, User user);

    User getUserById(Long userId);

    void deleteUserById(Long userId);
}