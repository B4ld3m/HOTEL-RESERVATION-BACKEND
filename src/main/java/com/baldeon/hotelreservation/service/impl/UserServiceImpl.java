package com.baldeon.hotelreservation.service.impl;

import com.baldeon.hotelreservation.mapper.UserMapper;
import com.baldeon.hotelreservation.model.dto.AuthResponseDTO;
import com.baldeon.hotelreservation.model.dto.LoginDTO;
import com.baldeon.hotelreservation.model.dto.UserProfileDTO;
import com.baldeon.hotelreservation.model.dto.UserRegisterDTO;
import com.baldeon.hotelreservation.model.entity.Customer;
import com.baldeon.hotelreservation.model.entity.Role;
import com.baldeon.hotelreservation.model.entity.User;
import com.baldeon.hotelreservation.repository.RoleRepository;
import com.baldeon.hotelreservation.repository.UserRepository;
import com.baldeon.hotelreservation.security.TokenProvider;
import com.baldeon.hotelreservation.security.UserPrincipal;
import com.baldeon.hotelreservation.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private TokenProvider tokenProvider;
    @Autowired
    private RoleRepository roleRepository;

    private AuthenticationManager authenticationManager;
    @Autowired
    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserMapper userMapper;

    @Override
    public UserProfileDTO registerCustomer(UserRegisterDTO userRegisterDTO) {
        Role role = roleRepository.findById(2).orElseThrow(null);
        return registerUserWithRole(userRegisterDTO, role);
    }

    @Override
    public AuthResponseDTO login(LoginDTO loginDTO) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDTO.getEmail(), loginDTO.getPassword())
        );

        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        User user = userPrincipal.getUser();

        String token = tokenProvider.createAccessToken(authentication);

        AuthResponseDTO authResponseDTO = userMapper.toAuthResponseDTO(user, token);
        return authResponseDTO;

    }
    private UserProfileDTO registerUserWithRole(UserRegisterDTO userRegisterDTO, Role role) {
        boolean existsByEmail = userRepository.existsByEmail(userRegisterDTO.getEmail());


        if (existsByEmail) {
            throw new IllegalArgumentException("Email ingresado ya se encuentra registrado.");
        }

        Role rolefound = roleRepository.findByName(role.getName())
                .orElseThrow(() -> new IllegalArgumentException("Rol no encontrado."));

        userRegisterDTO.setPassword(passwordEncoder.encode(userRegisterDTO.getPassword()));

        User user = userMapper.toUserEntity(userRegisterDTO);
        user.setRole(rolefound);

        if(Objects.equals(role.getName(), "ROLE_CUSTOMER")){
            Customer customer = new Customer();
            customer.setName(userRegisterDTO.getName());
            customer.setLastname(userRegisterDTO.getLastname());
            customer.setEdad(userRegisterDTO.getEdad());
            customer.setEmail(userRegisterDTO.getEmail());
            customer.setRegisterDate(LocalDate.now());
            customer.setUser(user);
            user.setCustomer(customer);
        }
        else if(Objects.equals(role.getName(), "ROLE_ADMIN")){

        }
        User savedUser = userRepository.save(user);
        return userMapper.toUserProfileDTO(savedUser);
    }
}
