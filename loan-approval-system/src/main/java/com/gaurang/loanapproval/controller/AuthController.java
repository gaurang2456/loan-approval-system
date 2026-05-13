package com.gaurang.loanapproval.controller;

import com.gaurang.loanapproval.dto.*;
import com.gaurang.loanapproval.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/signup")
    public SignupResponseDTO signup(
            @Valid @RequestBody SignupRequestDTO request) {

        authService.register(
                request.getName(),
                request.getEmail(),
                request.getPassword()
        );

        return new SignupResponseDTO(
                "User registered successfully"
        );
    }

    @PostMapping("/login")
    public LoginResponseDTO login(
            @Valid @RequestBody LoginRequestDTO request) {

        return authService.login(
                request.getEmail(),
                request.getPassword()
        );
    }
    @PostMapping("/refresh")
    public RefreshTokenResponseDTO refreshToken(
            @RequestBody RefreshTokenRequestDTO request) {

        return authService.refreshToken(
                request.getRefreshToken()
        );
    }
}
