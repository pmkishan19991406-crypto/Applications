
package com.kishan.employeeexpensetracker.service;

import java.util.List;

import com.kishan.employeeexpensetracker.entity.User;

public interface IUserService {

    User registerUser(User user);

    User loginUser(String email, String password);

    User changeManager(Long employeeId, String currentManagerEmail);

    List<User> getEmployees();
}
