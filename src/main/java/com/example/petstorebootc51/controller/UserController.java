package com.example.petstorebootc51.controller;

import com.example.petstorebootc51.entity.User;
import com.example.petstorebootc51.repository.UserRepository;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @ApiOperation(value="Create user")
    @PostMapping
    public ResponseEntity<User> save(@RequestBody User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().build();
        }

        User save = userRepository.save(user);
        return ResponseEntity.ok(save);
    }

    @ApiOperation(value="Get user by user name")
    @GetMapping("/{username}")
    public ResponseEntity<User> getUserByUsername(@PathVariable("username") String username) {
        if (username == null | userRepository.getUserByUsername(username).isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        Optional<User> userByUsername = userRepository.getUserByUsername(username);
        return  ResponseEntity.ok(userByUsername.get());
    }

//    @PutMapping("/{username}")
//    public ResponseEntity<User> updateUserByUsername(@PathVariable("username") String username) {
//        if (username == null | userRepository.getUserByUsername(username).isEmpty()) {
//            return ResponseEntity.badRequest().build();
//        }
//
//        Optional<User> userByUsername = userRepository.getUserByUsername(username);
//        User updateUser = userByUsername.get();
//
//
//    }

    @ApiOperation(value="Delete user")
    @DeleteMapping("/{username}")
    public void deleteUserByUsername(@PathVariable("username") String username) {
        if (username == null | userRepository.getUserByUsername(username).isEmpty()) {

        }

        Optional<User> userByUsername = userRepository.getUserByUsername(username);
        userByUsername.ifPresent(user -> userRepository.delete(user));
    }

    @ApiOperation(value="Logs user into the system")
    @GetMapping("/login")
    public String login(String username, String password, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "denied";
        }

        if (userRepository.getUserByUsername(username).isEmpty()) {
            return "denied";
        } else if (userRepository.getUserByUsername(username).get().getPassword().equals(password)) {
            return "success";
        } else {
            return "denied";
        }
    }
}
