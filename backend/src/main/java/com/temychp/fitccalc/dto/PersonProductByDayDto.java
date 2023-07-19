package com.temychp.fitccalc.dto;

import com.temychp.fitccalc.models.Meal;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
@ToString
public class PersonProductByDayDto {

    private Integer person;

    private Integer product;

    private int weight;

    private Meal meal;

}