package com.temychp.fitccalc.models;

import com.temychp.fitccalc.models.person.Person;
import com.temychp.fitccalc.models.product.Product;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@Getter
@ToString
@Entity
@Table(name = "day_meal")
public class PersonProductByDay {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date")
    private Instant date;

    @ManyToOne
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    private Person person;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;

    @Column(name = "weight")
    private int weight;

    @Enumerated(EnumType.STRING)
    private Meal meal;

public void setPerson(Person person) {
    this.person=person;
    this.person.getPersonProducts().add(this);
}
    public void setProduct(Product product) {
        this.product=product;
        this.product.getPersonProducts().add(this);
    }

}