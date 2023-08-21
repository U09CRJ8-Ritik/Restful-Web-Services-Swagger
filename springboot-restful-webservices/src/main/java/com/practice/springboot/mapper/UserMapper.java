package com.practice.springboot.mapper;

import com.practice.springboot.dto.UserDto;
import com.practice.springboot.entity.User;

public class UserMapper {

    // Convert User JPA Entity to UserDto
    public static UserDto mapToUserDto(User user) {

        UserDto userDto = new UserDto(user.getId(), user.getFirstName(), user.getLastName(), user.getEmail());

        return userDto;
    }

    // Convert UserDto into User JPA Entity
    public static User mapToUser(UserDto userDto) {

        User user = new User(userDto.getId(), userDto.getFirstName(), userDto.getLastName(), userDto.getEmail());

        return user;
    }

}
