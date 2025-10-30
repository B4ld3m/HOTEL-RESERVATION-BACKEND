package com.baldeon.hotelreservation.api;

import com.baldeon.hotelreservation.model.dto.AuthResponseDTO;
import com.baldeon.hotelreservation.model.dto.LoginDTO;
import com.baldeon.hotelreservation.model.dto.UserProfileDTO;
import com.baldeon.hotelreservation.model.dto.UserRegisterDTO;
import com.baldeon.hotelreservation.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:8080/api/v1")
public class AuthController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserProfileDTO> registerCustomer(@Valid @RequestBody UserRegisterDTO userRegisterDTO) {
        UserProfileDTO userProfileDTO = userService.registerCustomer(userRegisterDTO);
        return new ResponseEntity<>(userProfileDTO, HttpStatus.CREATED);
    }

    @CrossOrigin(origins = "http://localhost:8080/api/v1")
    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@Valid @RequestBody LoginDTO loginDTO) {
        AuthResponseDTO authResponseDTO = userService.login(loginDTO);
        return new ResponseEntity<>(authResponseDTO, HttpStatus.OK);
    }

}