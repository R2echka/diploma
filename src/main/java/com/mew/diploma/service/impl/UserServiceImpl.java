package com.mew.diploma.service.impl;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.mew.diploma.dto.UpdatePasswordDTO;
import com.mew.diploma.dto.UpdateUserDTO;
import com.mew.diploma.mapper.UserMapper;
import com.mew.diploma.model.Image;
import com.mew.diploma.model.User;
import com.mew.diploma.repository.ImageRepository;
import com.mew.diploma.repository.UserRepository;
import com.mew.diploma.service.UserService;


@Service
public class UserServiceImpl implements UserService {

    
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;
    private final ImageRepository imageRepository;
    private final UserMapper mapper;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, 
        ImageRepository imageRepository, UserMapper mapper){
        this.userRepository = userRepository;
        this.encoder = passwordEncoder;
        this.imageRepository = imageRepository;
        this.mapper = mapper;
    }

    @Override
    public User getUser(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public void setPassword(String email, UpdatePasswordDTO updatePassword) {
        User user = getUser(email);
        user.setPassword(encoder.encode(updatePassword.getNewPassword()));
        userRepository.save(user);
    }

    @Override
    public User changeUser(String email, UpdateUserDTO updateUser) {
        User user = getUser(email);
        user.setFirstName(updateUser.getFirstName());
        user.setLastName(updateUser.getLastName());
        user.setPhone(updateUser.getPhone());
        userRepository.save(user);
        return user;
    }

    @Override
    public String changeAvatar(String email, MultipartFile avatar) {
        User user = getUser(email);
        Image image = new Image();

        try {
                image.setData(avatar.getBytes());
                image.setMediaType(avatar.getContentType());
                image.setFilePath(avatar.getOriginalFilename());
            } catch (Exception e) {
            }
        Image savedImage = imageRepository.save(image);
        user.setImageId(savedImage.getId());
        userRepository.save(user);
        return mapper.toDto(user).getImageId();
    }
}
