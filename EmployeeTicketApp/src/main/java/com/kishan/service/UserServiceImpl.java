package com.kishan.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.kishan.entity.User;
import com.kishan.repository.IUserRepository;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserRepository urepo;
    
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User saveUser(User user) {
    	 user.setPassword(passwordEncoder.encode(user.getPassword()));
        return urepo.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return urepo.findAll();
    }

    @Override
    public User getUserById(Integer id) {
        return urepo.findById(id).orElse(null);
    }

    @Override
    public User updateUser(User user) {
        return urepo.save(user);
    }

    @Override
    public void deleteUser(Integer id) {
        urepo.deleteById(id);
    }
}