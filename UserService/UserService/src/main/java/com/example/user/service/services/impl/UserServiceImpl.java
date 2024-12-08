package com.example.user.service.services.impl;

import com.example.user.service.entities.User;
import com.example.user.service.exceptions.ResourceNotFound;
import com.example.user.service.repositories.UserRepository;
import com.example.user.service.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User saveUser(User user) {
        String uid = UUID.randomUUID().toString();
        user.setUid(uid);
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public User getUser(String uid) {
        return userRepository.findById(uid).orElseThrow(()-> new ResourceNotFound("User id not found"));
    }
}
