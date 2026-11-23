package com.kishan.employeeexpensetracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.kishan.employeeexpensetracker.entity.User;
import com.kishan.employeeexpensetracker.service.IUserService;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:5173")
public class UserController {

    @Autowired
    private IUserService userService;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody User user) {

        try {
            user.setRole(User.Role.EMPLOYEE);

            userService.registerUser(user);

            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("User registered successfully");

        } catch (IllegalArgumentException e) {

            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody User user) {

        try {
            userService.loginUser(user.getEmail(), user.getPassword());

            return ResponseEntity.ok("Login successful");

        } catch (IllegalArgumentException e) {

            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(e.getMessage());
        }
    }
}