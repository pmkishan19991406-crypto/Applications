
package com.kishan.securevault.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.*;

import com.kishan.securevault.service.AuthService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    // Register a new user
    @PostMapping("/register")
    public Map<String, String> register(
            @RequestBody Map<String, String> request) {

        String name = request.get("name");
        String email = request.get("email");
        String password = request.get("password");

        String message = authService.register(
                name, email, password);

        return Map.of("message", message);
    }

    // Login an existing user
    @PostMapping("/login")
    public Map<String, String> login(
            @RequestBody Map<String, String> request) {

        String email = request.get("email");
        String password = request.get("password");

        String token = authService.login(email, password);

        return Map.of(
                "message", "Login successful",
                "token", token
        );
    }
}
