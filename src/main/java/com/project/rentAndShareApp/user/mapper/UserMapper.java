package com.project.rentAndShareApp.user.mapper;

import com.project.rentAndShareApp.user.dto.UserDto;
import com.project.rentAndShareApp.user.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserDto toUserDto(User user) {
        return new UserDto(
                user.getId(),
                user.getName(),
                user.getEmail()
        );
    }

    public User toUser(UserDto userDto) {
        return new User(
                userDto.getId(),
                userDto.getName(),
                userDto.getEmail()
        );
    }
}