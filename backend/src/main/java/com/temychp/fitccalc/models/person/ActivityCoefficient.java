package com.temychp.fitccalc.models.person;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum ActivityCoefficient {

    ONE(1.2),
    TWO(1.375),
    THREE(1.4625),
    FOUR(1.550),
    FIVE(1.6375),
    SIX(1.725),
    SEVEN(1.9);

    private final Double activityCoefficient;

    public Double getActivityCoefficientValue() {
        return activityCoefficient;
    }
}