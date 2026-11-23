
package com.kishan.employeeexpensetracker.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.kishan.employeeexpensetracker.entity.User;
import com.kishan.employeeexpensetracker.security.JwtService;
import com.kishan.employeeexpensetracker.service.IUserService;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:5173")
public class UserController {

    private final IUserService userService;
    private final JwtService jwtService;

    public UserController(IUserService userService, JwtService jwtService) {
        this.userService = userService;
        this.jwtService = jwtService;
    }

    // REGISTER
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) {

        try {
            User savedUser = userService.registerUser(user);

            Map<String, Object> response = new HashMap<>();

            response.put("message", "Registration successful");
            response.put("name", savedUser.getName());
            response.put("email", savedUser.getEmail());
            response.put("role", savedUser.getRole().name());

            return ResponseEntity.status(HttpStatus.CREATED).body(response);

        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(
                    Map.of("message", e.getMessage()));
        }
    }

    // LOGIN
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody User user) {

        try {
            User loggedInUser = userService.loginUser(
                    user.getEmail(),
                    user.getPassword()
            );

            String token = jwtService.generateToken(
                    loggedInUser.getEmail(),
                    loggedInUser.getRole().name()
            );

            Map<String, Object> response = new HashMap<>();

            response.put("message", "Login successful");
            response.put("token", token);
            response.put("name", loggedInUser.getName());
            response.put("email", loggedInUser.getEmail());
            response.put("role", loggedInUser.getRole().name());

            return ResponseEntity.ok(response);

        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
                    Map.of("message", e.getMessage()));
        }
    }

    // GET ALL EMPLOYEES
    @GetMapping("/employees")
    public ResponseEntity<List<User>> getEmployees() {

        List<User> employees = userService.getEmployees();

        return ResponseEntity.ok(employees);
    }

    // CHANGE MANAGER
    @PutMapping("/change-manager/{employeeId}")
    public ResponseEntity<?> changeManager(
            @PathVariable Long employeeId,
            Authentication authentication) {

        try {
            String currentManagerEmail = authentication.getName();

            User newManager = userService.changeManager(
                    employeeId,
                    currentManagerEmail
            );

            return ResponseEntity.ok(
                    Map.of(
                            "message", "Manager successfully changed",
                            "newManager", newManager.getName(),
                            "email", newManager.getEmail()
                    )
            );

        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(
                    Map.of("message", e.getMessage()));
        }
    }
}
