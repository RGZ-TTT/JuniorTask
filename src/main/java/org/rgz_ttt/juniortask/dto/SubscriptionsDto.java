package org.rgz_ttt.juntask.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.rgz_ttt.juntask.model.User;

public record SubscriptionsDto(
        Long id,

        @NotBlank(message = "Service name cannot be empty")
        @Size(max = 20, message = "Service name must be less than 30 characters")
        String serviceName,

        Long userId
) {
}