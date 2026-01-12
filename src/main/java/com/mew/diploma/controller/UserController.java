package com.mew.diploma.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.mew.diploma.dto.UpdateUserDTO;
import com.mew.diploma.mapper.UserMapper;
import com.mew.diploma.service.UserService;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.multipart.MultipartFile;

import com.mew.diploma.dto.UpdatePasswordDTO;
import com.mew.diploma.dto.UserDTO;

@CrossOrigin(value = "http://localhost:3000")
@RestController()
@RequestMapping("/users")
public class UserController {
    
    private final UserService userService;
    private final UserMapper mapper;

    public UserController(UserService userService, UserMapper mapper) {
        this.userService = userService;
        this.mapper = mapper;
    }

    @PostMapping("/set_password")
    public ResponseEntity<?> setPassword(Authentication authentication, @RequestBody UpdatePasswordDTO updatePassword) {
        userService.setPassword(authentication.getName(), updatePassword);
        return ResponseEntity.status(200).build();
    }

    @GetMapping("/me")
    @ResponseStatus(HttpStatus.OK)
    public UserDTO getUser(Authentication authentication) {
        return mapper.toDto(userService.getUser(authentication.getName()));
    }

    @PatchMapping("/me")
    @ResponseStatus(HttpStatus.OK)
    public UpdateUserDTO changeUser(@RequestBody UpdateUserDTO updateUser, Authentication authentication) {
        return mapper.updateUser(userService.changeUser(authentication.getName(), updateUser));
    }

    @PatchMapping(value = "/me/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public void changeAvatar(@RequestParam MultipartFile image, Authentication authentication) {
        userService.changeAvatar(authentication.getName(), image);
    }
}
