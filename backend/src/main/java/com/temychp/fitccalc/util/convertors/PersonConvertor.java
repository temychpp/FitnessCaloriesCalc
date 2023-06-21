package com.temychp.fitccalc.util.convertors;

import com.temychp.fitccalc.dto.PersonDto;
import com.temychp.fitccalc.models.person.Person;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;

@AllArgsConstructor
public class PersonConvertor {

    private final ModelMapper modelMapper;

    public Person DtoToModel(PersonDto personDto) {
        return modelMapper.map(personDto, Person.class);
    }

    public PersonDto ModelToDto(Person person) {
        return modelMapper.map(person, PersonDto.class);
    }
}