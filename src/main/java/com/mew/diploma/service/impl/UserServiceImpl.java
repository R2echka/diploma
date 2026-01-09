package com.mew.diploma.service.impl;

import java.util.Optional;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.mew.diploma.mapper.UserMapper;
import com.mew.diploma.model.Avatar;
import com.mew.diploma.model.User;
import com.mew.diploma.repository.UserRepository;
import com.mew.diploma.service.UserService;


@Service
public class UserServiceImpl implements UserService {

    private final UserMapper mapper;
    private final UserRepository userRepository;

    public UserServiceImpl(UserMapper mapper, UserRepository userRepository){
        this.mapper = mapper;
        this.userRepository = userRepository;
    }

    public Authentication authefication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    @Override
    public User getUser() {
        return mapper.fromUserDetails((UserDetails)authefication().getPrincipal());
    }

    @Override
    public void setPassword(String password, String newPassword) {
        User user = getUser();
        user.setPassword(newPassword);
        userRepository.save(user);
    }

    @Override
    public User changeUser(String firstName, String lastName, String phone) {
        User user = getUser();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setPhone(phone);
        userRepository.save(user);
        return user;
    }

    @Override
    public void changeAvatar(Avatar avatar) {
        User user = getUser();
        user.setAvatar(avatar.getId());
        userRepository.save(user);
    }
}
