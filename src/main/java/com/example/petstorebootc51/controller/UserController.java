package com.example.petstorebootc51.controller;

import com.example.petstorebootc51.entity.User;
import com.example.petstorebootc51.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserRepository userRepository;


    @PostMapping
    public ResponseEntity<User> save(@RequestBody User user, BindingResult bindingResult) {

        User save = userRepository.save(user);
        return ResponseEntity.ok(save);
    }
}
