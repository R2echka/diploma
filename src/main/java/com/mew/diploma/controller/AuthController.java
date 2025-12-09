package com.mew.diploma.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mew.diploma.dto.Login;
import com.mew.diploma.dto.Register;
import com.mew.diploma.service.AuthService;


@CrossOrigin(value = "http://localhost:3000")
@RestController
public class AuthController {
    
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Login login) {
        if(authService.login(login)) {
            return ResponseEntity.status(200).build();
        }else{
            return ResponseEntity.status(400).build();
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Register register) {
        if(authService.register(register)) {
            return ResponseEntity.status(201).build();
        }else{
            return ResponseEntity.status(400).build();
        }
    }
}
