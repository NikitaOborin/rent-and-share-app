package com.project.rentAndShareApp.user.controller;

import com.project.rentAndShareApp.user.dto.UserDto;
import com.project.rentAndShareApp.user.entity.User;
import com.project.rentAndShareApp.user.mapper.UserMapper;
import com.project.rentAndShareApp.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Validated
@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;

    @Autowired
    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @GetMapping()
    public List<UserDto> getListUsers() {
        log.info("UserController: getListUsers(): start");
        List<UserDto> userDtoList = new ArrayList<>();

        for (User user : userService.getListUsers()) {
            userDtoList.add(userMapper.toUserDto(user));
        }

        return userDtoList;
    }

    @PostMapping()
    public UserDto addUser(@RequestBody @Valid UserDto userDto) {
        log.info("UserController: addUser(): start with userDtoId='{}'", userDto);
        User addedUser = userService.addUser(userMapper.toUser(userDto));

        return userMapper.toUserDto(addedUser);
    }

    @PatchMapping("/{userId}")
    public UserDto updateUser(@PathVariable Long userId,
                              @RequestBody UserDto userDto) {
        log.info("UserController: updateUser(): start with userDtoId={}", userId);
        User updatedUser = userService.updateUser(userId, userMapper.toUser(userDto));

        return userMapper.toUserDto(updatedUser);
    }

    @GetMapping("/{id}")
    public UserDto getUserById(@PathVariable("id") Long userId) {
        log.info("UserController: getUserById(): start with userId={}", userId);
        User user = userService.getUserById(userId);

        return userMapper.toUserDto(user);
    }

    @DeleteMapping("{id}")
    public void deleteUserById(@PathVariable("id") Long userId) {
        log.info("UserController: deleteUserById(): start with userId={}", userId);
        userService.deleteUserById(userId);
    }
}
