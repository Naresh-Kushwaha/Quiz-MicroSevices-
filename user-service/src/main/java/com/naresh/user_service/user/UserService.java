package com.naresh.user_service.user;
import com.naresh.user_service.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repository;
    private final UserMapper mapper;
    public String createUser(UserRequest req){
        UserEntity user=mapper.toUserEntity(req);
        this.repository.save(user);
        return  "User registered successfully";
    }

    public String updateUser( UserRequest req) {
        var user=repository.findById(req.id())
                .orElseThrow(()->new UserNotFoundException(
                        String.format("Cannot update user ::no user found with ID: %d",req.id())
                ));
        mergeUser(user,req);
        repository.save(user);
        return "Successfully Updated";
    }
    private void mergeUser(UserEntity user,UserRequest req){
        if(StringUtils.isNotBlank(req.email())){
            user.setEmail(req.email());
        }
        if(StringUtils.isNotBlank(req.password())){
            user.setPassword(req.password());
        }
    }

    public List<UserResponse> findAllUsers() {
       return repository.findAll().stream().map(
               mapper::toUserResponse)
                .collect(Collectors.toList());

    }

    public ResponseEntity<UserResponse> findUserById(UUID id) {
        Optional<UserEntity> user=this.repository.findById(id);
        return user.map(userEntity -> ResponseEntity.ok(mapper.toUserResponse(userEntity))).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }

    public void deleteUser(UUID id) {
        repository.deleteById(id);
    }
}
