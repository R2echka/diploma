package com.mew.diploma.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.security.core.userdetails.UserDetails;

import com.mew.diploma.dto.Register;
import com.mew.diploma.model.User;

@Mapper(componentModel="spring")
public class UserMapper {

    public User fromRegister(Register register) {
        if (register == null) {
            return null;
        }
        
        User user = new User();
        user.setEmail(register.getEmail());
        user.setFirstName(register.getFirstName());
        user.setLastName(register.getLastName());
        user.setPassword(register.getPassword());
        user.setRole(register.getRole());
        user.setPhone(register.getPhone());
        return user;
    }
    
    public User fromUserDetails(UserDetails userDetails) {
        if (userDetails == null) {
            return null;
        }
        
        User user = new User();
        user.setEmail(userDetails.getUsername());
        user.setPassword(userDetails.getPassword());
        return user;
    }
}
