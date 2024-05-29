package br.com.quattys.backend.application.controller;

import br.com.quattys.backend.application.dto.mapper.UserMapper;
import br.com.quattys.backend.application.dto.request.CreateUserRequest;
import br.com.quattys.backend.application.dto.response.UserResponse;
import br.com.quattys.backend.domain.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/v1/user")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    @PostMapping
    public ResponseEntity<String> createUser(@Valid @RequestBody CreateUserRequest createUserRequest) {

        var user =  userMapper.toDomain(createUserRequest);
        var message = userService.createUser(user);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(message)
                .toUri();

        return ResponseEntity.created(uri).build();
    }

    @GetMapping("/me")
    public ResponseEntity<UserResponse> getCurrentUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        var user = userService.getUserByUsername(authentication.getName());
        var userResponse = userMapper.toResponse(user);
        return ResponseEntity.ok(userResponse);
    }
}
