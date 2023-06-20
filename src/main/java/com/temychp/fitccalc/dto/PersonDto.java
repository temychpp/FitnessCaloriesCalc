package com.temychp.fitccalc.dto;

import com.temychp.fitccalc.models.Product;
import com.temychp.fitccalc.models.PersonAnthropometry;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.hibernate.annotations.Cascade;

import java.util.List;

@Setter
@Getter
@ToString
public final class PersonDto {

    @NotEmpty(message = "Name should not be empty")
    private String name;

    @NotEmpty(message = "Name should not be empty")
    private String email;

    @NotEmpty(message = "Name should not be empty")
    private String password;

    @OneToOne(mappedBy = "person")
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    private PersonAnthropometry personAnthropometry;

    @ManyToMany
    @JoinTable(name = "person_product",
            joinColumns = @JoinColumn(name = "person_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    private List<Product> products;

}