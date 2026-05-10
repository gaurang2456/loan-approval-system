package com.gaurang.loanapproval.controller;

import com.gaurang.loanapproval.dto.LoginResponseDTO;
import com.gaurang.loanapproval.service.AuthService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/signup")
    public String signup(@RequestParam String name,
                         @RequestParam String email,
                         @RequestParam String password) {

        return authService.register(name, email, password);
    }
    @PostMapping("/login")
    public LoginResponseDTO login(@RequestParam String email,
                        @RequestParam String password) {

        String token = authService.login(email, password);

        return new LoginResponseDTO(token);
    }
}
