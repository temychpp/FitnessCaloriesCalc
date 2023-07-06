package com.temychp.fitccalc.dto;

import com.temychp.fitccalc.models.person.Gender;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public final class PersonAnthropometryDto {

    private Long id;

    private Gender gender;

    private Integer age;

    private Float weight;

    private Integer height;

}