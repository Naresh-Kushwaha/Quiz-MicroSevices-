package com.naresh.user_service.user;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UserMapper {
    public UserEntity toUserEntity(UserRequest req){
        if(req==null){
            return null;
        }
        return UserEntity.builder()
                .id(UUID.randomUUID())
                .username(req.username())
                .email(req.email())
                .password(req.password())
                .role(req.role())
                .build();
    }
    public UserResponse toUserResponse(UserEntity userEntity){
        return new UserResponse(
                userEntity.getId(),
                userEntity.getUsername(),
                userEntity.getEmail()
        );
    }
}
