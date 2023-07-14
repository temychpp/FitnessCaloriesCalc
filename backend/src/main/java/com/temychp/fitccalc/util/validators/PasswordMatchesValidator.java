package com.temychp.fitccalc.util.validators;

import com.temychp.fitccalc.dto.PersonDto;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordMatchesValidator
        implements ConstraintValidator<PasswordMatches, Object> {

    @Override
    public void initialize(PasswordMatches constraintAnnotation) {
    }

    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext context) {
        PersonDto person = (PersonDto) obj;
        return person.getPassword().equals(person.getMatchingPassword());
    }
}