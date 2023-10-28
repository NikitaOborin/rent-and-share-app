package com.project.rentAndShareApp.user.repository;

import com.project.rentAndShareApp.exception.NotFoundException;
import com.project.rentAndShareApp.user.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Slf4j
@Repository
public class UserInMemoryRepository implements UserRepository {
    private final Map<Long, User> users = new HashMap<>();
    private Long counter = 1L;

    @Override
    public List<User> getListUsers() {
        return users.values().stream().toList();
    }

    @Override
    public User addUser(User user) {
        if (users.containsKey(user.getId())) {
            throw new IllegalArgumentException("user with id = " + user.getId() + " already exist");
        }

        for (User existUser : users.values()) {
            if (existUser.getEmail().equals(user.getEmail())) {
                throw new IllegalArgumentException("user with email = '" + user.getEmail() + "' already exist");
            }
        }

        user.setId(counter);

        users.put(counter, user);
        counter++;

        return user;
    }

    @Override
    public User updateUser(User user) {
        if (!users.containsKey(user.getId())) {
            throw new NotFoundException("user with id = " + user.getId() + " not found");
        }

        for (User existUser : users.values()) {
            if (existUser.getEmail().equals(user.getEmail()) && !Objects.equals(existUser.getId(), user.getId())) {
                throw new IllegalArgumentException("user with email = '" + user.getEmail() + "' already exist");
            }
        }

        User userInDb = users.get(user.getId());

        if (user.getName() != null) {
            userInDb.setName(user.getName());
        }

        if (user.getEmail() != null) {
            userInDb.setEmail(user.getEmail());
        }

        users.put(user.getId(), userInDb);

        return userInDb;
    }

    @Override
    public User getUserById(Long userId) {
        if (!users.containsKey(userId)) {
            throw new NotFoundException("user with id = " + userId + " not found");
        }

        return users.get(userId);
    }

    @Override
    public void deleteUserById(Long userId) {
        if (!users.containsKey(userId)) {
            throw new NotFoundException("user with id = " + userId + " not found");
        }

        users.remove(userId);
    }
}
