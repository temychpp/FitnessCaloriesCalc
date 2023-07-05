package com.temychp.fitccalc.configs;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FitccalcConfiguration {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

}