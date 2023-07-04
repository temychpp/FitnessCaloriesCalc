package com.temychp.fitccalc.util.convertors;

import com.temychp.fitccalc.models.person.ActivityCoefficient;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class ActivityCoefficientConverter implements AttributeConverter<ActivityCoefficient, Double> {

    @Override
    public Double convertToDatabaseColumn(ActivityCoefficient attribute) {
        return attribute.  getActivityCoefficientValue();
    }

    @Override
    public ActivityCoefficient convertToEntityAttribute(Double dbData) {
        return  ActivityCoefficient.ONE;// valueOf(String.valueOf(dbData));
    }
}