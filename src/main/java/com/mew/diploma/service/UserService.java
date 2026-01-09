package com.mew.diploma.service;

import org.springframework.web.multipart.MultipartFile;

import com.mew.diploma.dto.UpdatePasswordDTO;
import com.mew.diploma.dto.UpdateUserDTO;
import com.mew.diploma.model.User;

public interface UserService {

    void setPassword(String email, UpdatePasswordDTO updatePassword);
    User getUser(String email);
    User changeUser(String email, UpdateUserDTO updateUser);
    String changeAvatar(String email, MultipartFile avatar);
}
