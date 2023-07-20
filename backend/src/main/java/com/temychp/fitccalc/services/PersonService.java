package com.temychp.fitccalc.services;

import com.temychp.fitccalc.dto.LoginDto;
import com.temychp.fitccalc.dto.PersonDto;
import com.temychp.fitccalc.dto.RegistrationDto;
import com.temychp.fitccalc.dto.UpdatePersonDto;
import com.temychp.fitccalc.models.person.Person;
import com.temychp.fitccalc.models.person.Role;
import com.temychp.fitccalc.repositories.PersonRepository;
import com.temychp.fitccalc.util.convertors.PersonConvertor;
import com.temychp.fitccalc.util.convertors.RegistrationConvertor;
import com.temychp.fitccalc.util.exceptions.InvalidPersonException;
import com.temychp.fitccalc.util.exceptions.PersonDuplicateException;
import com.temychp.fitccalc.util.exceptions.PersonNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Slf4j
@AllArgsConstructor
@Transactional(readOnly = true)
@Service
public class PersonService {

    private final PersonConvertor personConvertor;

    private final RegistrationConvertor registrationConvertor;

    private final PersonRepository personRepository;

    private PasswordEncoder passwordEncoder;

    public List<Person> findAll() {
        return personRepository.findAll();
    }

    public Person findOne(Long id) {
        Optional<Person> foundUser = personRepository.findById(id);
        return foundUser.orElse(null);
    }

    @Transactional
    public void save(Person person) {
        enrichPerson(person);
        personRepository.save(person);
    }

    private void enrichPerson(Person person) {
        if (person.getCreatedAt() == null) {
            person.setCreatedAt(Instant.now());
        }
        person.setChangedAt(Instant.now());
        if (person.getRole().getPersonRole().equals("null")) {
            person.setRole(Role.valueOf("USER"));
        }
        person.setPassword(passwordEncoder.encode(person.getPassword()));
    }

    @Transactional
    public void update(UpdatePersonDto updatePersonDto) {
        Long id = getPersonIdFromContext();
        Person person = findOne(id);
        person.setName(updatePersonDto.getName());
        person.setEmail(updatePersonDto.getEmail());
        person.setPassword(updatePersonDto.getPassword());
        save(person);
    }

    @Transactional
    public void delete(Long id) {
        personRepository.deleteById(id);
    }

    public Optional<Person> findByName(String name) {
        return personRepository.findByName(name);
    }

    public PersonDto login(LoginDto loginDto) throws PersonNotFoundException, InvalidPersonException {
        log.info("credentialsDto ={}", loginDto);
        Person person = personRepository.findByName(loginDto.getUsername())
                .orElseThrow(PersonNotFoundException::new);
        log.info("person ={}", person);
        if (passwordEncoder.matches(loginDto.getPassword(), person.getPassword())) {
            return personConvertor.modelToDto(person);
        }
        throw new InvalidPersonException("Invalid password");
    }

    @Transactional
    public PersonDto register(RegistrationDto registrationDto) {
        Optional<Person> optionalPerson = personRepository.findByNameOrEmail(registrationDto.getName(), registrationDto.getEmail());
        if (optionalPerson.isPresent()) {
            throw new PersonDuplicateException("Person already exists");
        }
        Person person = registrationConvertor.dtoToModel(registrationDto);
        save(person);
        log.info("Зарегистрирован новый пользователь {}", person);

        return personConvertor.modelToDto(person);
    }

    public Long getPersonIdFromContext() {
        Optional<Person> person;

        if (SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof java.util.Optional) {
            person = (Optional<Person>) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        } else {
            PersonDto personDto = (PersonDto) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            person = findByName(personDto.getName());
        }

        if (person.isEmpty()) {
            throw new PersonNotFoundException();
        }

        Long id = person.get().getId();
        log.info("нашли person c id={}", id);
        return id;
    }
}