
package com.kishan.employeeexpensetracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kishan.employeeexpensetracker.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);

    boolean existsByEmail(String email);

}
