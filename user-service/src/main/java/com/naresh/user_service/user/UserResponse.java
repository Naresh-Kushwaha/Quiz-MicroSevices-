package com.naresh.user_service.user;


import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public record UserResponse(
        UUID id,
        String username,
        String email
) {
}
