package com.temychp.fitccalc.util.convertors;

import com.temychp.fitccalc.dto.RegistrationDto;
import com.temychp.fitccalc.models.person.Person;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class RegistrationConvertor extends ConvertorDto<Person, RegistrationDto> {

    public RegistrationConvertor(ModelMapper modelMapper) {
        super(Person.class, RegistrationDto.class, modelMapper);
    }
}