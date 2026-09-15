package com.kishan.service;

import java.util.List;

import com.kishan.entity.User;

public interface IUserService {
	
	public User saveUser(User user);

    public List<User> getAllUsers();

    public User getUserById(Integer id);

    public User updateUser(User user);

    public void deleteUser(Integer id);

}
