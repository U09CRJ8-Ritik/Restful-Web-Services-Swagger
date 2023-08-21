package com.practice.springboot.service.impl;

import com.practice.springboot.dto.UserDto;
import com.practice.springboot.entity.User;
import com.practice.springboot.exceptions.EmailAlreadyExistsException;
import com.practice.springboot.exceptions.ResourceNotFoundException;
import com.practice.springboot.mapper.UserMapper;
import com.practice.springboot.repository.UserRepository;
import com.practice.springboot.service.UserService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    private ModelMapper modelMapper;

    @Override
    public UserDto createUser(UserDto userDto) {

        // Convert UserDto to user entity

//        User user = UserMapper.mapToUser(userDto);

        User user = modelMapper.map(userDto, User.class);

        Optional<User> optionalUser = userRepository.findByEmail(userDto.getEmail());

        if (optionalUser.isPresent()) {
            throw new EmailAlreadyExistsException("Email Already Exists for a User");
        }

        User savedUser = userRepository.save(user);

        // Convert User JPA entity to UserDto

//        UserDto savedUserDto = UserMapper.mapToUserDto(savedUser);

        UserDto savedUserDto = modelMapper.map(savedUser, UserDto.class);

        return savedUserDto;

    }

    @Override
    public UserDto getUserById(Long userId) {


        User user = userRepository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("User", "id", userId));

//        UserDto userDto = UserMapper.mapToUserDto(user);

        UserDto userDto = modelMapper.map(user, UserDto.class);

        return userDto;

    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();

//        List<UserDto> userDtoList = users.stream().map(UserMapper::mapToUserDto).collect(Collectors.toList());

        List<UserDto> userDtoList = users.stream().map((user) -> modelMapper.map(user, UserDto.class)).collect(Collectors.toList());

        return userDtoList;

    }

    @Override
    public UserDto updateUser(UserDto user) {
        User existingUser = userRepository.findById(user.getId()).orElseThrow(
                () -> new ResourceNotFoundException("User", "id", user.getId()));

        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setEmail(user.getEmail());

        User updatedUser = userRepository.save(existingUser);

//        UserDto updatedUserDto = UserMapper.mapToUserDto(updatedUser);

        UserDto updatedUserDto = modelMapper.map(updatedUser, UserDto.class);


        return updatedUserDto;
    }

    @Override
    public void deleteUser(Long userId) {

        User existingUser = userRepository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("User", "id", userId));

        userRepository.deleteById(userId);
    }
}
