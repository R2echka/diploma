package com.mew.diploma.service;

import com.mew.diploma.model.Avatar;
import com.mew.diploma.model.User;

public interface UserService {

    void setPassword(String password, String newPassword);
    User getUser();
    User changeUser(String firstName, String lastName, String phone);
    void changeAvatar(Avatar avatar);
}
