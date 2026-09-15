package com.kishan.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RoleController {

    @GetMapping("/admin/dashboard")
    public String adminDashboard() {
        return "Welcome Admin";
    }

    @GetMapping("/employee/dashboard")
    public String employeeDashboard() {
        return "Welcome Employee";
    }
}