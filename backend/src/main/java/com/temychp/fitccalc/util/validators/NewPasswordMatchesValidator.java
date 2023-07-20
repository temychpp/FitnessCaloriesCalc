package com.temychp.fitccalc.util.validators;

import com.temychp.fitccalc.dto.UpdatePersonDto;
import com.temychp.fitccalc.models.person.Person;
import com.temychp.fitccalc.services.PersonService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;

@Slf4j
@AllArgsConstructor
public class NewPasswordMatchesValidator
        implements ConstraintValidator<NewPasswordMatches, Object> {

    private final PersonService personService;

    private final PasswordEncoder passwordEncoder;

    @Override
    public void initialize(NewPasswordMatches constraintAnnotation) {
    }

    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext context) {
        if (!(obj instanceof UpdatePersonDto)) {
            throw new IllegalArgumentException("не UpdatePersonDto!");
        }
        UpdatePersonDto updatedPerson = (UpdatePersonDto) obj;
        Long id = personService.getPersonIdFromContext();

        Person person = personService.findOne(id);
        String oldBDPassword = person.getPassword();
        String password = updatedPerson.getOldPassword();

        return passwordEncoder.matches(password, oldBDPassword);
    }
}