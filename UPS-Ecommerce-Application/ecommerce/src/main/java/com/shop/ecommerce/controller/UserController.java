package com.shop.ecommerce.controller;

import com.shop.ecommerce.dto.request.LoginRequest;
import com.shop.ecommerce.dto.request.RegisterRequest;
import com.shop.ecommerce.dto.response.AuthResponse;
import com.shop.ecommerce.dto.response.UserResponse;
import com.shop.ecommerce.service.UserService;
import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public AuthResponse register(@Valid @RequestBody RegisterRequest request) {
        return userService.register(request);
    }

    @PostMapping("/login")
    public AuthResponse login(@Valid @RequestBody LoginRequest request) {
        return userService.login(request);
    }

    @GetMapping("/me")
    public UserResponse me(Authentication authentication) {
        return userService.currentUser(authentication.getName());
    }
}
