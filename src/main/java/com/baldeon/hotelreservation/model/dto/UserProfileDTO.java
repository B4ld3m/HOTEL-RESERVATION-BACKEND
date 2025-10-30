package com.baldeon.hotelreservation.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserProfileDTO {
    private String name;
    private String lastname;
    private int edad;
    private String email;
    private String password;
}
