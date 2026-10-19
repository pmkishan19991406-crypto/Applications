
package com.kishan.securevault.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.kishan.securevault.entity.User;
import com.kishan.securevault.repository.UserRepository;
import com.kishan.securevault.security.JwtService;

@Service
public class AuthService {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private JwtService jwtService;

    public AuthService(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            JwtService jwtService) {

        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    // Register a new user
    public String register(
            String name, String email, String password) {

        if (name == null || name.isBlank()
                || email == null || email.isBlank()
                || password == null || password.isBlank()) {
            throw new IllegalArgumentException(
                    "All fields are required");
        }

        if (password.length() < 8) {
            throw new IllegalArgumentException(
                    "Password must contain at least 8 characters");
        }

        if (userRepository.existsByEmail(email)) {
            throw new IllegalArgumentException(
                    "Email already registered");
        }

        User user = new User();

        user.setName(name);
        user.setEmail(email);

        String encodedPassword =
                passwordEncoder.encode(password);

        user.setPassword(encodedPassword);

        userRepository.save(user);

        return "Registration successful";
    }

    // Login an existing user
    public String login(String email, String password) {

        if (email == null || email.isBlank()
                || password == null || password.isBlank()) {
            throw new IllegalArgumentException(
                    "Email and password are required");
        }

        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Invalid email or password"));

        boolean passwordCorrect =
                passwordEncoder.matches(
                        password, user.getPassword());

        if (!passwordCorrect) {
            throw new IllegalArgumentException(
                    "Invalid email or password");
        }

        String token =
                jwtService.generateToken(user.getEmail());

        return token;
    }

    // Change the logged-in user's password
    public String changePassword(
            String email,
            String currentPassword,
            String newPassword) {

        if (currentPassword == null
                || currentPassword.isBlank()
                || newPassword == null
                || newPassword.isBlank()) {
            throw new IllegalArgumentException(
                    "All password fields are required");
        }

        if (newPassword.length() < 8) {
            throw new IllegalArgumentException(
                    "New password must contain at least 8 characters");
        }

        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new IllegalArgumentException("User not found"));

        boolean currentPasswordCorrect =
                passwordEncoder.matches(
                        currentPassword, user.getPassword());

        if (!currentPasswordCorrect) {
            throw new IllegalArgumentException(
                    "Current password is incorrect");
        }

        boolean samePassword =
                passwordEncoder.matches(
                        newPassword, user.getPassword());

        if (samePassword) {
            throw new IllegalArgumentException(
                    "New password must differ from current password");
        }

        String encodedPassword =
                passwordEncoder.encode(newPassword);

        user.setPassword(encodedPassword);

        userRepository.save(user);

        return "Password changed successfully";
    }
}
