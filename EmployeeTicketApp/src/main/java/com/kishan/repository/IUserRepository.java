package com.kishan.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kishan.entity.User;

public interface IUserRepository extends JpaRepository<User, Integer> {

	public User findByUsername(String username);

}
