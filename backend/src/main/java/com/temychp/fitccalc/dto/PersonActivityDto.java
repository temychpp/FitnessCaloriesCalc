package com.temychp.fitccalc.dto;

import com.temychp.fitccalc.models.person.ActivityCoefficient;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public final class PersonActivityDto {

    private Long stepsByDay;

    private Long fitnessByDay;

    private Long aerobicsByDay;

    private ActivityCoefficient activityCoefficient;

}