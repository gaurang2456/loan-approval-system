package com.gaurang.loanapproval.service;

import com.gaurang.loanapproval.config.JwtUtil;
import com.gaurang.loanapproval.dto.LoginResponseDTO;
import com.gaurang.loanapproval.dto.RefreshTokenResponseDTO;
import com.gaurang.loanapproval.entity.User;
import com.gaurang.loanapproval.enums.Role;
import com.gaurang.loanapproval.exception.InvalidPasswordException;
import com.gaurang.loanapproval.exception.UserNotFoundException;
import com.gaurang.loanapproval.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AuthService {

    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder,
                       JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    public String register(String name, String email, String password) {

        if (userRepository.findByEmail(email).isPresent()) {
            return "User already exists";
        }

        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password)); //  hashing
        user.setRole(Role.USER);
        user.setCreatedAt(LocalDateTime.now());

        userRepository.save(user);

        return "User registered successfully";
    }
    public LoginResponseDTO login(String email, String password) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new InvalidPasswordException("Invalid password");
        }

        String accessToken = jwtUtil.generateToken(
                user.getEmail(),
                user.getRole().name()
        );

        String refreshToken = jwtUtil.generateRefreshToken(
                user.getEmail()
        );

        return new LoginResponseDTO(
                accessToken,
                refreshToken
        );
    }
    public RefreshTokenResponseDTO refreshToken(String refreshToken) {

        if (!jwtUtil.isTokenValid(refreshToken)) {
            throw new RuntimeException("Invalid refresh token");
        }

        String email = jwtUtil.extractEmail(refreshToken);

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        String newAccessToken = jwtUtil.generateToken(
                user.getEmail(),
                user.getRole().name()
        );

        return new RefreshTokenResponseDTO(newAccessToken);
    }
}
