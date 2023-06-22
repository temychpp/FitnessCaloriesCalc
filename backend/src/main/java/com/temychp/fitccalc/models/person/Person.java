package com.temychp.fitccalc.models.person;

import com.temychp.fitccalc.models.PersonProductByDay;
import com.temychp.fitccalc.models.product.Product;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.annotations.Cascade;

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

    @OneToOne(
            mappedBy = "person",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            optional = false)
    private PersonAnthropometry personAnthropometry;

    @OneToOne(
            mappedBy = "person",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            optional = false)
    private PersonActivity personActivity;

    @OneToMany(mappedBy = "person")
    private List<PersonProductByDay> personProducts;

}