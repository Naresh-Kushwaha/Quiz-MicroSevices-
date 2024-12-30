package com.naresh.user_service.user;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;


@JsonInclude(JsonInclude.Include.NON_EMPTY)
public record UserRequest(
        UUID id,
   @NotEmpty(message="Username is required")
   String username,
   @NotEmpty(message="Email is required")
   @NotNull(message="Email is required")
   @Email(message="Email is invalid")
   String email,
   @NotEmpty(message="Password is required")
   String password,
   @NotNull(message="Role is required")
   Role role
) {
}
