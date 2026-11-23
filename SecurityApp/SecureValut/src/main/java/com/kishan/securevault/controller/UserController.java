
package com.kishan.securevault.controller;

import java.util.Map;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.kishan.securevault.entity.User;
import com.kishan.securevault.repository.UserRepository;
import com.kishan.securevault.service.AuthService;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private UserRepository userRepository;
    private AuthService authService;

    // Constructor
    public UserController(
            UserRepository userRepository,
            AuthService authService) {

        this.userRepository = userRepository;
        this.authService = authService;
    }

    // View logged-in user's profile
    @GetMapping("/me")
    public Map<String, String> getMyProfile(
            Authentication authentication) {

        // Get logged-in user's email
        String email = authentication.getName();

        // Find user in database
        User user = userRepository.findByEmail(email)
                .orElse(null);

        // Check whether user exists
        if (user == null) {
            return Map.of(
                    "message", "User not found");
        }

        // Return profile as JSON
        return Map.of(
                "name", user.getName(),
                "email", user.getEmail());
    }

    // Change logged-in user's password
    @PutMapping("/change-password")
    public Map<String, String> changePassword(
            @RequestBody PasswordRequest request,
            Authentication authentication) {

        // Get logged-in user's email
        String email = authentication.getName();

        // Change password using service
        String message = authService.changePassword(
                email,
                request.getCurrentPassword(),
                request.getNewPassword());

        // Return success message as JSON
        return Map.of("message", message);
    }
}
