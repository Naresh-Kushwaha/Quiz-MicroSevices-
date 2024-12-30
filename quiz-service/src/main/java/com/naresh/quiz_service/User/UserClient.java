package com.naresh.quiz_service.User;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;
import java.util.UUID;

@FeignClient(name="user-service",url = "${application.config.user-url}")
public interface UserClient {
    @GetMapping("/{user-id}")
    Optional<UserResponse> findUserById(@PathVariable("user-id") UUID id);
}
