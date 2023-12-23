package com.project.user.service;

import com.project.user.dto.UserRequestDto;
import com.project.user.dto.UserResponseDto;

import java.util.List;

public interface UserService {
    List<UserResponseDto> getListUsers();

    UserResponseDto addUser(UserRequestDto userDto);

    UserResponseDto updateUser(UserRequestDto userDto, Long userId);

    UserResponseDto getUserById(Long userId);

    void deleteUserById(Long userId);
}