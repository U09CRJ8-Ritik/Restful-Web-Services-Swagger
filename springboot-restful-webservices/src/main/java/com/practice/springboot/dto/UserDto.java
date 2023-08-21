package com.practice.springboot.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Schema(
        description = "User DTO Model Description"
)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private Long id;

    @Schema(
            description = "User First Name"
    )
    // name should not be null or empty
    @NotEmpty(message = "User First Name Should Not be null or empty")
    private String firstName;

    @Schema(
            description = "User Last Name"
    )
    // name should not be null or empty
    @NotEmpty(message = "User Last Name Should Not be null or empty")
    private String lastName;

    @Schema(
            description = "User Email Address"
    )
    // email should not be null or empty
    // should be email
    @NotEmpty(message = "User Email Should Not be null or empty")
    @Email(message = "Email Address should be Valid")
    private String email;
}
