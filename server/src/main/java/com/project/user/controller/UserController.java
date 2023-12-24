package com.project.user.controller;

import com.project.user.dto.UserRequestDto;
import com.project.user.dto.UserResponseDto;
import com.project.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@Slf4j
@Validated
@RestController
@RequestMapping("users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserResponseDto> getListUsers() {
        log.info("UserController: getListUsers(): start");
        return userService.getListUsers();
    }

    @PostMapping
    public UserResponseDto addUser(@RequestBody @Valid UserRequestDto userDto) {
        log.info("UserController: addUser(): start with userDto='{}'", userDto);
        return userService.addUser(userDto);
    }

    @PatchMapping("{userId}")
    public UserResponseDto updateUser(@RequestBody UserRequestDto userDto,
                                      @PathVariable Long userId) {
        log.info("UserController: updateUser(): start with userId={} and userDto='{}'", userId, userDto);
        return userService.updateUser(userDto, userId);
    }

    @GetMapping("{userId}")
    public UserResponseDto getUserById(@PathVariable Long userId) {
        log.info("UserController: getUserById(): start with userId={}", userId);
        return userService.getUserById(userId);
    }

    @DeleteMapping("{userId}")
    public void deleteUserById(@PathVariable Long userId) {
        log.info("UserController: deleteUserById(): start with userId={}", userId);
        userService.deleteUserById(userId);
    }
}