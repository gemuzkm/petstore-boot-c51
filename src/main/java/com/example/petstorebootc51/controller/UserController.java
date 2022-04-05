package com.example.petstorebootc51.controller;

import com.example.petstorebootc51.entity.User;
import com.example.petstorebootc51.repository.UserRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
@Api(tags = "user")
public class UserController {
    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @ApiOperation(value = "Create user", notes = "This can only be done by the logged in user.")
    @PostMapping
    public void save(@RequestBody @ApiParam(value = "Created user object") User user) {
        userRepository.save(user);
    }

    @ApiOperation(value = "Get user by user name")
    @GetMapping("/{username}")
    public ResponseEntity<User> getUserByUsername(@PathVariable("username")
                                                  @ApiParam(value = "The name that needs to be fetched. Use user1 for testing.", example = "username") String username) {
        if (username == null | userRepository.getUserByUsername(username).isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        Optional<User> userByUsername = userRepository.getUserByUsername(username);
        return ResponseEntity.ok(userByUsername.get());
    }

    @ApiOperation(value = "Update user", notes = "This can only be done by the logged in user.")
    @PutMapping("/{username}")
    public ResponseEntity<User> updateUserByUsername(@PathVariable("username")
                                                     @ApiParam(value = "name that need to be updated", example = "username") String username,
                                                     @ApiParam(value = "Updated user object") @RequestBody User user) {
        if (username == null | userRepository.getUserByUsername(username).isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        Optional<User> userByUsername = userRepository.getUserByUsername(username);
        User updateUser = new User();
        if (userByUsername.isPresent()) {
            updateUser = userRepository.save(user);
        }

        return ResponseEntity.ok(userRepository.save(updateUser));

    }

    @ApiOperation(value = "Delete user", notes = "This can only be done by the logged in user.")
    @DeleteMapping("/{username}")
    public void deleteUserByUsername(@PathVariable("username") @ApiParam(value = "The name that needs to be deleted", example = "username") String username) {
        if (username == null | userRepository.getUserByUsername(username).isEmpty()) {

        }

        Optional<User> userByUsername = userRepository.getUserByUsername(username);
        userByUsername.ifPresent(userRepository::delete);
    }

    @ApiOperation(value = "Logs user into the system")
    @GetMapping("/login")
    public String login(@ApiParam(value = "The user name for login", example = "username", required = true) String username,
                        @ApiParam(value = "TThe password for login in clear text", example = "password", required = true) String password,
                        BindingResult bindingResult) {
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

    @GetMapping("/logout")
    public void logout() {

    }

    @ApiOperation(value = "Creates list of users with given input array")
    @PostMapping("/createWithArray")
    public void createWithArray(@RequestBody @ApiParam(value = "List of user object") User[] users) {
        List<User> userList = new ArrayList<>();

        for (int i = 0; i < users.length; i++) {
            userList.add(userRepository.save(users[i]));
        }
    }

    @ApiOperation(value = "Creates list of users with given input array")
    @PostMapping("/createWithList")
    public void createWithList(@RequestBody @ApiParam(value = "List of user object") List<User> userList) {
        userRepository.saveAll(userList);
    }
}
