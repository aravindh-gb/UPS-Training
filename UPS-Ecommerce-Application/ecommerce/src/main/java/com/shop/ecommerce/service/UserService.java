package com.shop.ecommerce.service;

import com.shop.ecommerce.dto.request.LoginRequest;
import com.shop.ecommerce.dto.request.RegisterRequest;
import com.shop.ecommerce.dto.response.AuthResponse;
import com.shop.ecommerce.dto.response.UserResponse;
import com.shop.ecommerce.model.User;
import com.shop.ecommerce.repository.UserRepository;
import com.shop.ecommerce.security.JwtUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder,
                       AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    @Transactional
    public AuthResponse register(RegisterRequest request) {
        if (userRepository.existsByEmailIgnoreCase(request.email())) {
            throw new IllegalArgumentException("An account with this email already exists.");
        }
        User user = userRepository.save(new User(request.name().trim(),
                request.email().trim().toLowerCase(), passwordEncoder.encode(request.password())));
        return new AuthResponse(jwtUtil.generateToken(toUserDetails(user)), UserResponse.from(user));
    }

    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.email().trim().toLowerCase(), request.password()));
        User user = userRepository.findByEmailIgnoreCase(request.email())
                .orElseThrow(() -> new IllegalArgumentException("Email or password is incorrect."));
        return new AuthResponse(jwtUtil.generateToken(toUserDetails(user)), UserResponse.from(user));
    }

    @Transactional(readOnly = true)
    public UserResponse currentUser(String email) {
        return UserResponse.from(userRepository.findByEmailIgnoreCase(email)
                .orElseThrow(() -> new IllegalArgumentException("Authenticated user was not found.")));
    }

    private UserDetails toUserDetails(User user) {
        return org.springframework.security.core.userdetails.User.withUsername(user.getEmail())
                .password(user.getPassword())
                .roles(user.getRole())
                .build();
    }
}
