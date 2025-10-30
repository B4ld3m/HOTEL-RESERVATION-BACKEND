package com.baldeon.hotelreservation.mapper;

import com.baldeon.hotelreservation.model.dto.AuthResponseDTO;
import com.baldeon.hotelreservation.model.dto.LoginDTO;
import com.baldeon.hotelreservation.model.dto.UserProfileDTO;
import com.baldeon.hotelreservation.model.dto.UserRegisterDTO;
import com.baldeon.hotelreservation.model.entity.User;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class UserMapper {

    private final ModelMapper modelMapper;

    public User toUserEntity(UserRegisterDTO userRegisterDTO) {
        return modelMapper.map(userRegisterDTO, User.class);
    }

    public User toEntity(UserProfileDTO userProfileDTO) {
        User user =  modelMapper.map(userProfileDTO, User.class);

        return user;

    }
    public UserProfileDTO toUserProfileDTO(User user) {
        UserProfileDTO userProfileDTO = modelMapper.map(user, UserProfileDTO.class);
        if(user.getCustomer() != null){
            userProfileDTO.setName(user.getCustomer().getName());
            userProfileDTO.setLastname(user.getCustomer().getLastname());
            userProfileDTO.setEdad(user.getCustomer().getEdad());
            userProfileDTO.setEmail(user.getCustomer().getEmail());
        }
        if(user.getRole().getName().equals("ROLE_ADMIN")){
            userProfileDTO.setEmail("Admin@Admin.com");
            userProfileDTO.setName("Admin");
        }
        return userProfileDTO;
    }

    public User toUserEntityLogin(LoginDTO loginDTO) {
        return modelMapper.map(loginDTO, User.class);
    }

    public AuthResponseDTO toAuthResponseDTO(User user, String token) {
        AuthResponseDTO authResponseDTO = new AuthResponseDTO();
        authResponseDTO.setToken(token);

        String name = (user.getCustomer()!= null)? user.getCustomer().getName()
                : (user.getRole().getName().equals("ROLE_ADMIN"))? "Admin" : "";
        authResponseDTO.setName(name);
        authResponseDTO.setId(user.getId());
        authResponseDTO.setRole(user.getRole().getName());

        return authResponseDTO;
    }

}
