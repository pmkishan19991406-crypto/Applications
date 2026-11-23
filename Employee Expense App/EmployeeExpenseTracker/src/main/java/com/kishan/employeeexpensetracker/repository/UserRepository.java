
package com.kishan.employeeexpensetracker.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kishan.employeeexpensetracker.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);

    boolean existsByEmail(String email);

    List<User> findByRole(User.Role role);
}
