package com.kishan.securevault.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kishan.securevault.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);
}
