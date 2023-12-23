package com.project.user.service;

import com.project.exception.NotFoundException;
import com.project.exception.UserEmailAlreadyExistException;
import com.project.user.dto.UserRequestDto;
import com.project.user.dto.UserResponseDto;
import com.project.user.entity.User;
import com.project.user.mapper.UserMapper;
import com.project.user.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public List<UserResponseDto> getListUsers() {
        log.info("UserService: getListUsers(): start");
        List<User> users = userRepository.findAll();
        List<UserResponseDto> userDtoList = new ArrayList<>();

        for (User user : users) {
            userDtoList.add(userMapper.toUserDto(user));
        }

        return userDtoList;
    }

    @Override
    public UserResponseDto addUser(UserRequestDto userDto) {
        log.info("UserService: addUser(): start with userDtoId='{}'", userDto);
        User user;

        try {
            user = userRepository.save(userMapper.toUser(userDto));
        } catch (DataIntegrityViolationException e) {
            throw new UserEmailAlreadyExistException("user with email:" + userDto.getEmail() + " already exist");
        }

        return userMapper.toUserDto(user);
    }

    @Override
    public UserResponseDto updateUser(UserRequestDto userDto, Long userId) {
        log.info("UserService: updateUser(): start with userId={} and userDto:'{}'", userId, userDto);
        if (!userRepository.existsById(userId)) {
            throw new NotFoundException("user with id=" + userId + " not found");
        }

        User user = userMapper.toUser(userDto);
        User existUser = userRepository.getReferenceById(userId);

        if (userRepository.existsUserByEmail(user.getEmail()) && !existUser.getEmail().equals(user.getEmail())) {
            throw new UserEmailAlreadyExistException("user with email:" + user.getEmail() + " already exist");
        }

        if (user.getName() != null) {
            existUser.setName(user.getName());
        }

        if (user.getEmail() != null) {
            existUser.setEmail(user.getEmail());
        }

        return userMapper.toUserDto(userRepository.save(existUser));
    }

    @Override
    public UserResponseDto getUserById(Long userId) {
        log.info("UserService: getUserById(): start with userId={}", userId);
        Optional<User> optionalUser = userRepository.findById(userId);

        if (optionalUser.isEmpty()) {
            throw new NotFoundException("user with id=" + userId + " not found");
        }

        return userMapper.toUserDto(optionalUser.get());
    }

    @Override
    public void deleteUserById(Long userId) {
        log.info("UserService: deleteUserById(): start with userId={}", userId);
        userRepository.deleteById(userId);
    }
}