package com.temychp.fitccalc.dto;

import com.temychp.fitccalc.models.Meal;
import com.temychp.fitccalc.models.product.Product;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
@ToString
public class PersonProductByDayDto {

    private Product product;

    private int weight;

    private Meal meal;

}