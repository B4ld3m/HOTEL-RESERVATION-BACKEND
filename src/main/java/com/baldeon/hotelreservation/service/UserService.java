package com.baldeon.hotelreservation.service;

import com.baldeon.hotelreservation.model.dto.AuthResponseDTO;
import com.baldeon.hotelreservation.model.dto.LoginDTO;
import com.baldeon.hotelreservation.model.dto.UserProfileDTO;
import com.baldeon.hotelreservation.model.dto.UserRegisterDTO;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    UserProfileDTO registerCustomer(UserRegisterDTO userRegisterDTO);
    AuthResponseDTO login(LoginDTO loginDTO);
}
