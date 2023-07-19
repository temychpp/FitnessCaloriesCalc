package com.temychp.fitccalc.util.validators;

import com.temychp.fitccalc.models.person.Person;
import com.temychp.fitccalc.services.PersonService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@AllArgsConstructor
@Component
public class PersonNameValidator implements Validator {

    private final PersonService personService;

    @Override
    public boolean supports(Class<?> aClass) {
        return Person.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Person person = (Person) o;
        if (personService.findByName(person.getName()).isPresent())
                errors.rejectValue("name", "", "This person name is already taken!");
    }
}