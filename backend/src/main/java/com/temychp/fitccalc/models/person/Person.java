package com.temychp.fitccalc.models.person;

import com.temychp.fitccalc.models.PersonProductByDay;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.Instant;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@Getter
@ToString(exclude = {"personAnthropometry", "personActivity", "personProducts",})
@Entity
@Table(name = "person")
public final class Person {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Name should not be empty")
    @Size(min = 2, max = 50, message = "Name should be between 2 and 50 characters")
    @Column(name = "name")
    private String name;

    @Email
    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "created_at")
    private Instant createdAt;

    @Column(name = "changed_at")
    private Instant changedAt;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Embedded
    private PersonAnthropometry personAnthropometry;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride( name = "stepsByDay", column = @Column(name = "steps_by_day")),
            @AttributeOverride( name = "fitnessByDay", column = @Column(name = "fitness_by_day")),
            @AttributeOverride( name = "aerobicsByDay", column = @Column(name = "aerobics_by_day"))
    })
    private PersonActivity personActivity;

    @OneToMany(mappedBy = "person")
    private List<PersonProductByDay> personProducts;

}