package com.temychp.fitccalc.util.convertors;

import com.temychp.fitccalc.dto.PersonProductByDayDto;
import com.temychp.fitccalc.models.PersonProductByDay;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class PersonProductByDayConvertor extends ConvertorDto<PersonProductByDay, PersonProductByDayDto> {

    public PersonProductByDayConvertor(ModelMapper modelMapper) {
        super(PersonProductByDay.class, PersonProductByDayDto.class, modelMapper);
    }
}