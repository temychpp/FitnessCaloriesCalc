package com.temychp.fitccalc.models.person;

import com.temychp.fitccalc.models.person.Gender;
import com.temychp.fitccalc.models.person.Person;
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
@Table(name = "person_anthropometry")
public final class PersonAnthropometry {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Min(value = 0, message = "Age should be greater than 0")
    @Column(name = "age")
    private Integer age;

    @Column(name = "weight")
    private Float weight;

    @Column(name = "height")
    private Integer height;

    @OneToOne
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    private Person person;

}