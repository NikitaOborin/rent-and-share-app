package com.project.rentAndShareApp.user.mapper;

import com.project.rentAndShareApp.user.dto.UserRequestDto;
import com.project.rentAndShareApp.user.dto.UserResponseDto;
import com.project.rentAndShareApp.user.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserResponseDto toUserDto(User user) {
        return new UserResponseDto(
                user.getId(),
                user.getName(),
                user.getEmail()
        );
    }

    public User toUser(UserRequestDto userDto) {
        User user = new User();

        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());

        return user;
    }
}