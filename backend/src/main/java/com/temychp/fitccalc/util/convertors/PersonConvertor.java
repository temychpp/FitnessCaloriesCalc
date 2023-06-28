package com.temychp.fitccalc.util.convertors;

import com.temychp.fitccalc.dto.PersonDto;
import com.temychp.fitccalc.models.person.Person;
import org.modelmapper.ModelMapper;

public class PersonConvertor extends ConvertorDto<Person, PersonDto> {

    public PersonConvertor(ModelMapper modelMapper) {
        super(Person.class, PersonDto.class, modelMapper);
    }
}