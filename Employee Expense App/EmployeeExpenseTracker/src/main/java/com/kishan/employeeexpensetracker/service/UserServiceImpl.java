
package com.kishan.employeeexpensetracker.service;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kishan.employeeexpensetracker.entity.User;
import com.kishan.employeeexpensetracker.repository.UserRepository;
import com.kishan.employeeexpensetracker.security.JwtService;

@Service
public class UserServiceImpl implements IUserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public UserServiceImpl(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            JwtService jwtService) {

        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    // Register user
    @Override
    public User registerUser(User user) {

        if (userRepository.existsByEmail(user.getEmail())) {
            throw new IllegalArgumentException("Email already registered");
        }

        // First user becomes manager
        if (userRepository.count() == 0) {
            user.setRole(User.Role.MANAGER);
        } else {
            user.setRole(User.Role.EMPLOYEE);
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return userRepository.save(user);
    }

    // Login user
    @Override
    public User loginUser(String email, String password) {

        User user = userRepository.findByEmail(email);

        if (user == null) {
            throw new IllegalArgumentException("Invalid email or password");
        }

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new IllegalArgumentException("Invalid email or password");
        }

        return user;
    }

    // Change manager
    @Override
    @Transactional
    public User changeManager(Long employeeId, String currentManagerEmail) {

        User currentManager = userRepository.findByEmail(currentManagerEmail);

        if (currentManager == null) {
            throw new IllegalArgumentException("Current manager not found");
        }

        if (currentManager.getRole() != User.Role.MANAGER) {
            throw new IllegalArgumentException("Only the current manager can transfer the role");
        }

        User newManager = userRepository.findById(employeeId)
                .orElseThrow(() ->
                        new IllegalArgumentException("Employee not found"));

        if (currentManager.getId().equals(newManager.getId())) {
            throw new IllegalArgumentException("You are already the manager");
        }

        if (newManager.getRole() != User.Role.EMPLOYEE) {
            throw new IllegalArgumentException("Selected user is not an employee");
        }

        // Transfer manager role
        currentManager.setRole(User.Role.EMPLOYEE);
        newManager.setRole(User.Role.MANAGER);

        userRepository.save(currentManager);
        userRepository.save(newManager);

        return newManager;
    }

    // Get all employees
    @Override
    public List<User> getEmployees() {

        return userRepository.findByRole(User.Role.EMPLOYEE);
    }
}
