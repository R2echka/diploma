package com.mew.diploma.mapper;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.mew.diploma.dto.Register;
import com.mew.diploma.dto.UpdateUserDTO;
import com.mew.diploma.dto.UserDTO;
import com.mew.diploma.model.Image;
import com.mew.diploma.model.User;
import com.mew.diploma.repository.ImageRepository;

@Component
public class UserMapper {

    private final ImageRepository imageRepository;

    public UserMapper(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }


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

    public UserDTO toDto(User user){
        if (user == null) {
            return null;
        }
        UserDTO userDTO = new UserDTO();
        userDTO.setEmail(user.getEmail());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setId(user.getId());
        userDTO.setLastName(user.getLastName());
        userDTO.setPhone(user.getPhone());
        userDTO.setRole(user.getRole());

        if (user.getImageId() != null) {
            Image image = imageRepository.findById((long)user.getImageId());
            String base64Image = java.util.Base64.getEncoder()
                    .encodeToString(image.getData());
            String mediaType = image.getMediaType();
            userDTO.setImageId("data:" + mediaType + ";base64," + base64Image);
        }
        return userDTO;
    }

    public UpdateUserDTO updateUser(User user){

        UpdateUserDTO changeUser = new UpdateUserDTO();
        changeUser.setFirstName(user.getFirstName());
        changeUser.setLastName(user.getLastName());
        changeUser.setPhone(user.getPhone());

        return changeUser;
    }
}
