package com.baldeon.hotelreservation.model.dto;

import lombok.Data;

@Data
public class AuthResponseDTO {
    private Integer id;
    private String Token;
    private String name;
    private String role;

}