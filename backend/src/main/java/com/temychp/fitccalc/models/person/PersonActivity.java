package com.temychp.fitccalc.models.person;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@Getter
@ToString
@Embeddable
public final class PersonActivity {

    @Column(name = "steps_by_day")
    private Long stepsByDay;

    @Column(name = "fitness_by_day")
    private Long fitnessByDay;

    @Column(name = "aerobics_by_day")
    private Long aerobicsByDay;

}