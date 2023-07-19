package com.temychp.fitccalc.models.product;

import com.temychp.fitccalc.models.PersonProductByDay;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@Getter
@ToString(exclude = {"personProducts"})
@Entity
@Table(name = "product")
public final class Product {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    @NotNull
    @Column(name = "name")
    private String name;

    @Column(name = "calories")
    private Float calories;

    @Column(name = "protein")
    private Float protein;

    @Column(name = "fat")
    private Float fat;

    @Column(name = "carbohydrates")
    private Float carbohydrates;

    @NotNull
    @Column(name="created_person_id")
    private Long createdUserId;

    @OneToMany(mappedBy = "product")
    private List<PersonProductByDay> personProducts;

}