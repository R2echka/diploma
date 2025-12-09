package com.mew.diploma.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.mew.diploma.model.Avatar;
import com.mew.diploma.model.User;
import com.mew.diploma.service.UserService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;

@CrossOrigin(value = "http://localhost:3000")
@RestController("/users")
public class UserController {
    
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/set_password")
    public ResponseEntity<?> setPassword(@RequestBody String password, String newPassword) {
        userService.setPassword(password, newPassword);
        return ResponseEntity.status(200).build();
    }

    @GetMapping("/me")
    @ResponseStatus(HttpStatus.OK)
    public User getUser() {
        return userService.getUser();
    }

    @PatchMapping("/me")
    @ResponseStatus(HttpStatus.OK)
    public User changUser(@RequestBody String firstName, String lastName, String phone) {
        return userService.changeUser(firstName, lastName, phone);
    }

    @PatchMapping("/me/image")
    @ResponseStatus(HttpStatus.OK)
    public void changAvatar(@RequestBody Avatar avatar) {
        userService.changeAvatar(avatar);
    }
    
}
