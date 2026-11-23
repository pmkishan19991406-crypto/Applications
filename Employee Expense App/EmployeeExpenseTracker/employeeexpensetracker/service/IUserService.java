package com.kishan.employeeexpensetracker.service;

import com.kishan.employeeexpensetracker.entity.User;

public interface IUserService {

    User registerUser(User user);

    User loginUser(String email, String password);

}