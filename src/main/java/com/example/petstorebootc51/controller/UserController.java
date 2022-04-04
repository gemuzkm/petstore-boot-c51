package com.example.petstorebootc51.controller;

import com.example.petstorebootc51.entity.User;
import com.example.petstorebootc51.repository.UserRepository;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @ApiOperation(value = "Create user", notes = "This can only be done by the logged in user.")
    @PostMapping
    public ResponseEntity<Void> save(@RequestBody User user, BindingResult bindingResult) {
        userRepository.save(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @ApiOperation(value = "Get user by user name")
    @GetMapping("/{username}")
    public ResponseEntity<User> getUserByUsername(@PathVariable("username") String username) {
        if (username == null | userRepository.getUserByUsername(username).isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        Optional<User> userByUsername = userRepository.getUserByUsername(username);
        return ResponseEntity.ok(userByUsername.get());
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

    @ApiOperation(value = "Delete user")
    @DeleteMapping("/{username}")
    public void deleteUserByUsername(@PathVariable("username") String username) {
        if (username == null | userRepository.getUserByUsername(username).isEmpty()) {

        }

        Optional<User> userByUsername = userRepository.getUserByUsername(username);
        userByUsername.ifPresent(user -> userRepository.delete(user));
    }

    @ApiOperation(value = "Logs user into the system")
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
