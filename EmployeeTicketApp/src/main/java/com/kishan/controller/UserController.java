package com.kishan.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.kishan.entity.User;
import com.kishan.service.IUserService;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private IUserService uservice;

    @PostMapping
    public User saveUser(@RequestBody User user) {
        return uservice.saveUser(user);
    }

    @GetMapping
    public List<User> getAllUsers() {
        return uservice.getAllUsers();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Integer id) {
        return uservice.getUserById(id);
    }

    @PutMapping
    public User updateUser(@RequestBody User user) {
        return uservice.updateUser(user);
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Integer id) {
        uservice.deleteUser(id);
        return "User deleted successfully";
    }
}