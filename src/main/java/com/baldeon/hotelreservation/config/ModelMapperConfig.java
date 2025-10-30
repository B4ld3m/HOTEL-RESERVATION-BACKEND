package com.baldeon.hotelreservation.config;

import com.baldeon.hotelreservation.mapper.UserMapper;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {
    @Bean
    public ModelMapper modelMapper() { return new ModelMapper(); }
}
