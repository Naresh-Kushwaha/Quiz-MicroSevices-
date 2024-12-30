package com.naresh.user_service.user;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService service;
    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody @Valid UserRequest req){
        return ResponseEntity.ok(service.createUser(req));
    }
    @PutMapping
    public ResponseEntity<Void> updateUser(@RequestBody @Valid UserRequest req){
        service.updateUser(req);
        return ResponseEntity.accepted().build();
    }
    @GetMapping
    public ResponseEntity<List<UserResponse>> findAll(){
        return ResponseEntity.ok(service.findAllUsers());
    }
    @GetMapping("/{user-id}")
    public ResponseEntity<UserResponse> findUserById(@PathVariable("user-id") UUID id){
        return service.findUserById(id);
    }
    @DeleteMapping("/{user-id}")
       public ResponseEntity<Void> deleteUser(@PathVariable("user-id") UUID id){
           service.deleteUser(id);
           return ResponseEntity.accepted().build();
        }

}
