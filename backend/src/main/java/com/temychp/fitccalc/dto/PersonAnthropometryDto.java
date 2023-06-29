package com.temychp.fitccalc.dto;

import com.temychp.fitccalc.models.person.Gender;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public final class PersonAnthropometryDto {

    private Gender gender;

    @Size(min = 1, max = 120, message = "age should be between 0 and 120 years")
    @Column(name = "age")
    private Integer age;

    @Size(min = 30, max = 544, message = "weight should be between 1 and 544 kg")
    @Column(name = "weight")
    private Float weight;

    @Size(min = 1, max = 272, message = "height should be between 100 and 272 cm")
    @Column(name = "height")
    private Integer height;

}