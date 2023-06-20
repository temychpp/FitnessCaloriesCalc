package com.temychp.fitccalc.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@Getter
@ToString
@Entity
@Table(name = "person_activity")
public final class PersonActivity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "steps_by_day")
    private Long stepsByDay;

    @Column(name = "fitness_by_day")
    private Long fitnessByDay;

    @Column(name = "aerobics_by_day")
    private Long aerobicsByDay;

    @OneToOne
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    private Person person;

}