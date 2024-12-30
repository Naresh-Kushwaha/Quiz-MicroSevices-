package com.naresh.quiz_service.User;

import java.util.UUID;

public record UserResponse(
        UUID id,
        String username,
        String email
) {
}
