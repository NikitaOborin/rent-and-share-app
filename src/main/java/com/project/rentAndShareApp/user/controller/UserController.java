package com.project.rentAndShareApp.user.controller;

import com.project.rentAndShareApp.user.dto.UserRequestDto;
import com.project.rentAndShareApp.user.dto.UserResponseDto;
import com.project.rentAndShareApp.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@Slf4j
@Validated
@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public List<UserResponseDto> getListUsers() {
        log.info("UserController: getListUsers(): start");
        return userService.getListUsers();
    }

    @PostMapping()
    public UserResponseDto addUser(@RequestBody @Valid UserRequestDto userDto) {
        log.info("UserController: addUser(): start with userDtoId='{}'", userDto);
        return userService.addUser(userDto);
    }

    @PatchMapping("/{userId}")
    public UserResponseDto updateUser(@RequestBody UserRequestDto userDto,
                                      @PathVariable Long userId) {
        log.info("UserController: updateUser(): start with userId={} and userDto:'{}'", userId, userDto);
        return userService.updateUser(userDto, userId);
    }

    @GetMapping("/{id}")
    public UserResponseDto getUserById(@PathVariable("id") Long userId) {
        log.info("UserController: getUserById(): start with userId={}", userId);
        return userService.getUserById(userId);
    }

    @DeleteMapping("{id}")
    public void deleteUserById(@PathVariable("id") Long userId) {
        log.info("UserController: deleteUserById(): start with userId={}", userId);
        userService.deleteUserById(userId);
    }
}