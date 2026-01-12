package com.mew.diploma.service.impl;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.mew.diploma.dto.Login;
import com.mew.diploma.dto.Register;
import com.mew.diploma.mapper.UserMapper;
import com.mew.diploma.model.User;
import com.mew.diploma.repository.UserRepository;
import com.mew.diploma.service.AuthService;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final UserMapper mapper;
    private final PasswordEncoder encoder;
    private final AuthenticationManager manager;


    public AuthServiceImpl(UserRepository userRepository, UserMapper mapper,
        PasswordEncoder passwordEncoder, AuthenticationManager manager) {
        this.userRepository = userRepository;
        this.mapper = mapper;
        this.encoder = passwordEncoder;
        this.manager = manager;
    }
    
    @Override
    public boolean login(Login login){
        try {
            Authentication authentication = manager.authenticate(
                new UsernamePasswordAuthenticationToken(
                    login.getEmail(), 
                    login.getPassword()
                )
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
            return true;
        }
        catch (Exception e) {
        return false;}
    }

    @Override
    public boolean register(Register register){
        if(userRepository.existsByEmail(register.getEmail())){
            return false;
        } else {
            User user = mapper.fromRegister(register);
            user.setPassword(encoder.encode(user.getPassword()));
            userRepository.save(user);
        return true;
        }
    }
}
