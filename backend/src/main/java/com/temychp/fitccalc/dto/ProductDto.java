package com.temychp.fitccalc.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Setter
@Getter
@ToString
public final class ProductDto {

    private Long id;

    @NotEmpty
    @NotNull
    private String name;

    private Float calories;

    private Float protein;

    private Float fat;

    private Float carbohydrates;

    @NotNull
    private Long createdUserId;

}