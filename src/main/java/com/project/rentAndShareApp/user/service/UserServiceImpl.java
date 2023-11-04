package com.project.rentAndShareApp.user.service;

import com.project.rentAndShareApp.exception.NotFoundException;
import com.project.rentAndShareApp.exception.UserEmailAlreadyExistException;
import com.project.rentAndShareApp.user.entity.User;
import com.project.rentAndShareApp.user.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getListUsers() {
        log.info("UserService: getListUsers(): start");
        return userRepository.findAll();
    }

    @Override
    public User addUser(User user) {
        log.info("UserService: addUser(): start with user:'{}'", user);
        if (userRepository.existsUserByEmail(user.getEmail())) {
            throw new UserEmailAlreadyExistException("user with email:" + user.getEmail() + " already exist");
        }

        return userRepository.save(user);
    }

    @Override
    public User updateUser(Long userId, User user) {
        log.info("UserService: updateUser(): start with userId={} and user:'{}'", userId, user);
        User userFromDb = getUserById(userId);

        if (user.getName() == null) {
            user.setName(userFromDb.getName());
        }

        if (user.getEmail() == null) {
            user.setEmail(userFromDb.getEmail());
        } else if (userRepository.existsUserByEmail(user.getEmail()) && !userFromDb.getEmail().equals(user.getEmail())) {
            throw new UserEmailAlreadyExistException("user with email:" + user.getEmail() + " already exist");
        }

        user.setId(userId);

        return userRepository.save(user);
    }

    @Override
    public User getUserById(Long userId) {
        log.info("UserService: getUserById(): start with userId={}", userId);
        Optional<User> optionalUser = userRepository.findById(userId);

        if (optionalUser.isEmpty()) {
            throw new NotFoundException("user with id=" + userId + " not found");
        }

        return optionalUser.get();
    }

    @Override
    public void deleteUserById(Long userId) {
        log.info("UserService: deleteUserById(): start with userId={}", userId);
        userRepository.deleteById(userId);
    }
}