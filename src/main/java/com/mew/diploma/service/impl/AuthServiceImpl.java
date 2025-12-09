package com.mew.diploma.service.impl;

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

    public AuthServiceImpl(UserRepository userRepository, UserMapper mapper) {
        this.userRepository = userRepository;
        this.mapper = mapper;
    }
    
    @Override
    public boolean login(Login login){
        User user = userRepository.findByEmail(login.getEmail());
        return user.getPassword().matches(login.getPassword());
    }

    @Override
    public boolean register(Register register){
        if(userRepository.existsByEmail(register.getEmail())){
            return false;
        } else {
            userRepository.save(mapper.fromRegister(register));
            return true;
        }
    }
}
