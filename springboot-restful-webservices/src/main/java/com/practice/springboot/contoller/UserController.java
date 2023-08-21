package com.practice.springboot.contoller;

import com.practice.springboot.dto.UserDto;
import com.practice.springboot.entity.User;
import com.practice.springboot.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(
        name = "CRUD REST API for USER Resource",
        description = "CRUD REST APIs - Create User,Update User,Get User,Get All Users,Delete User"
)
@RestController
@AllArgsConstructor
@RequestMapping("api/users")
public class UserController {

    private UserService userService;


    @Operation(
            summary = "Create User REST API",
            description = "Create User API is used to save user in a database"
    )
    @ApiResponse(
            responseCode = "201",
            description = "Http Status 201 Created"
    )
    // build create User API
    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody @Valid UserDto user) {

        UserDto savedUser = userService.createUser(user);

        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    @Operation(
            summary = "Get User by ID REST API",
            description = "Get User By ID REST API is get a single user from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 Created"
    )
    // build get user by Id API
    @GetMapping("{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable("id") Long userId) {
        UserDto userDto = userService.getUserById(userId);

        return new ResponseEntity<>(userDto, HttpStatus.OK);

    }

    @Operation(
            summary = "Get All Users REST API",
            description = "Get All Users API is used to get all the users from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 Created"
    )
    // build all get users API
    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {

        List<UserDto> users = userService.getAllUsers();

        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @Operation(
            summary = "Update User REST API",
            description = "Update REST API is used to update a particular user in the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 Created"
    )
    // build update user API
    @PutMapping("{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable("id") Long userId, @RequestBody @Valid UserDto user) {

        user.setId(userId);
        UserDto updatedUser = userService.updateUser(user);

        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @Operation(
            summary = "Delete User REST API",
            description = "Delete User REST API is used to delete a user from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 Created"
    )
    // build delete user API
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Long userId){

        userService.deleteUser(userId);

        return new ResponseEntity<>("User Deleted Successfully !!",HttpStatus.OK);

    }
}
