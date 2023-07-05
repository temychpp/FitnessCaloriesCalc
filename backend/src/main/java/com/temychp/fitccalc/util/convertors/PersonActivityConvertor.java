package com.temychp.fitccalc.util.convertors;

import com.temychp.fitccalc.dto.PersonActivityDto;
import com.temychp.fitccalc.models.person.PersonActivity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class PersonActivityConvertor extends ConvertorDto<PersonActivity, PersonActivityDto> {

    public PersonActivityConvertor(ModelMapper modelMapper) {
        super(PersonActivity.class, PersonActivityDto.class, modelMapper);
    }
}