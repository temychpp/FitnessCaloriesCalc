package com.temychp.fitccalc.models.person;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@Getter
@ToString
@Embeddable
public final class PersonAnthropometry {

    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    private Gender gender;

    @Min(value = 0, message = "Age should be greater than 0")
    @Column(name = "age")
    private Integer age;

    @Column(name = "weight")
    private Float weight;

    @Column(name = "height")
    private Integer height;

}