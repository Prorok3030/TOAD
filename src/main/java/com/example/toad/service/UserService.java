package com.example.toad.service;

import com.example.toad.models.UserEntity;
import com.example.toad.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService{
    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    private final UserRepository userRepository;

    public UserEntity findByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Username not found"));
    }

    public Boolean existsByUsername(String username){
        return userRepository.existsByUsername(username);
    }
    public void save(UserEntity user){
        userRepository.save(user);
    }



}
