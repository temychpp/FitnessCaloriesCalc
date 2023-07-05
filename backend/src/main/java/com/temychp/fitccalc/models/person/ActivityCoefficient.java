package com.temychp.fitccalc.models.person;

import java.util.HashMap;
import java.util.Map;

public enum ActivityCoefficient {

    ONE(1.2),
    TWO(1.375),
    THREE(1.4625),
    FOUR(1.550),
    FIVE(1.6375),
    SIX(1.725),
    SEVEN(1.9);

    private final Double activityCoefficient;

    private final static Map<Double, ActivityCoefficient> map = new HashMap<>();

    static {
        for (ActivityCoefficient e : ActivityCoefficient.values()
        ) {
            ActivityCoefficient.map.put(e.getActivityCoefficientValue(), e);
        }
    }

    ActivityCoefficient(Double activityCoefficient) {
        this.activityCoefficient = activityCoefficient;
    }

    public Double getActivityCoefficientValue() {
        return activityCoefficient;
    }

    public static ActivityCoefficient valueOfFromDouble(Double coef) {

        ActivityCoefficient result = map.get(coef);
        if (result == null) {
            throw new IllegalArgumentException();
        }
        return result;
    }
}