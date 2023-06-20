package com.temychp.fitccalc.dto;

import com.temychp.fitccalc.models.Person;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Setter
@Getter
@ToString
public final class ProductDto {

    @NotEmpty
    @NotNull
    private String name;

    private Float calories;

    private Float protein;

    private Float fat;

    private Float carbohydrates;

    @ManyToMany(mappedBy = "products")
    private List<Person> people;

}