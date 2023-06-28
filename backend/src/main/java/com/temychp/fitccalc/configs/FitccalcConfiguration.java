package com.temychp.fitccalc.configs;

import com.temychp.fitccalc.util.convertors.PersonConvertor;
import com.temychp.fitccalc.util.convertors.ProductConvertor;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FitccalcConfiguration {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public PersonConvertor personConvertor(ModelMapper modelMapper) {
        return new PersonConvertor(modelMapper);
    }

    @Bean
    public ProductConvertor productConvertor(ModelMapper modelMapper) {
        return new ProductConvertor(modelMapper);
    }

}