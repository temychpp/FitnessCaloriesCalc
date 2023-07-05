package com.temychp.fitccalc.util.convertors;

import com.temychp.fitccalc.dto.PersonAnthropometryDto;
import com.temychp.fitccalc.models.person.PersonAnthropometry;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class PersonAnthroConvertor extends ConvertorDto<PersonAnthropometry, PersonAnthropometryDto> {

    public PersonAnthroConvertor(ModelMapper modelMapper) {
        super(PersonAnthropometry.class, PersonAnthropometryDto.class, modelMapper);
    }
}