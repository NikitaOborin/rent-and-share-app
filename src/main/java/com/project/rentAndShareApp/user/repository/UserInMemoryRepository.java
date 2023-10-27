package com.project.rentAndShareApp.user.repository;

import com.project.rentAndShareApp.user.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;

@Slf4j
@Repository
public class UserInMemoryRepository implements UserRepository {

    @Override
    public List<User> getListUsers() {
        return null;
    }

    @Override
    public User addUser(User user) {
        return null;
    }

    @Override
    public User updateUser(User user) {
        return null;
    }

    @Override
    public User getUserById(Long userId) {
        return null;
    }

    @Override
    public void deleteUserById(Long userId) {

    }
}
