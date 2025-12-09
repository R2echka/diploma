package com.mew.diploma.service;

import com.mew.diploma.dto.Login;
import com.mew.diploma.dto.Register;

public interface AuthService {

    boolean login(Login login);
    boolean register(Register register);
}
