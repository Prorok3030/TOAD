package com.example.toad.controller;

import com.example.toad.models.Role;
import com.example.toad.models.UserEntity;
import com.example.toad.repo.RoleRepository;
import com.example.toad.repo.UserRepository;
import com.example.toad.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;
import java.util.Map;

@Controller
public class AuthController {

    private AuthenticationManager authenticationManager;
    private UserService userService;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, UserService userService, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @GetMapping("/registration")
    public String registration(UserEntity userEntity) {
        return "registration";
    }

    @PostMapping("/registration")
    public String registrationPost(UserEntity userEntity, Map<String, Object> model){
        if (userService.existsByUsername(userEntity.getUsername())){
            model.put("message","Такой пользователь уже существует");
            return "registration";
        }
        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
        Role roles = roleRepository.findByName("USER").get();
        userEntity.setRoles(Collections.singletonList(roles));
        userService.save(userEntity);

        return "redirect:/login";
    }


}
