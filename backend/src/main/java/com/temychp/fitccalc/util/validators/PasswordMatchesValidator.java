package com.temychp.fitccalc.util.validators;

import com.temychp.fitccalc.dto.RegistrationDto;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordMatchesValidator
        implements ConstraintValidator<PasswordMatches, Object> {

    @Override
    public void initialize(PasswordMatches constraintAnnotation) {
    }

    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext context) {
        RegistrationDto person = (RegistrationDto) obj;
        return person.getPassword().equals(person.getConfirmPassword());
    }
}