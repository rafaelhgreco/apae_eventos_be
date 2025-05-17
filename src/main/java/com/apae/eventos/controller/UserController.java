package com.apae.eventos.controller;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apae.eventos.dtos.requests.AuthenticationRequest;
import com.apae.eventos.dtos.requests.CreateUserRequest;
import com.apae.eventos.dtos.responses.AuthenticationResponse;
import com.apae.eventos.dtos.responses.UserDTO;
import com.apae.eventos.service.AuthService;
import com.apae.eventos.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final AuthService authService;

    public UserController(UserService userService, AuthService authService) {
        this.userService = userService;
        this.authService = authService;
    }

    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody @Valid CreateUserRequest request) {
        UserDTO user = userService.createUser(
                request.name(),
                request.email(),
                request.password()
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody @Valid AuthenticationRequest request) {
        return ResponseEntity.ok(authService.authenticate(request));
    }
    
    @GetMapping("/hello")
    public String helloWorld() {
        return "Hello, World!";
    }
}
