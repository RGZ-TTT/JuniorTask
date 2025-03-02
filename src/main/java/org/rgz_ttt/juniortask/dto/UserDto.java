package org.rgz_ttt.juntask.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserDto(
        Long id,

        @NotBlank (message = "name cannot be empty")
        @Size(min = 2, max = 20)
        String username,

        @NotBlank(message = "email cannot be empty")
        @Email
        String email
) {
}