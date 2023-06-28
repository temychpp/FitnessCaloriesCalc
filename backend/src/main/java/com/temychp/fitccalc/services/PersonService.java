package com.temychp.fitccalc.services;

import com.temychp.fitccalc.models.person.Person;
import com.temychp.fitccalc.repositories.PersonRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@AllArgsConstructor
@Transactional(readOnly = true)
@Service
public class PersonService {

    private final PersonRepository personRepository;

    public List<Person> findAll() {
        return personRepository.findAll();
    }

    public Person findOne(Long id) {
        Optional<Person> foundUser = personRepository.findById(id);
        return foundUser.orElse(null);
    }

    @Transactional
    public void save(Person person) {
        personRepository.save(person);
    }

    @Transactional
    public void update(Long id, Person updatedPerson) {
        updatedPerson.setId(id);
        personRepository.save(updatedPerson);
    }

    @Transactional
    public void delete(Long id) {
        personRepository.deleteById(id);
    }

    public Optional<Person> findByName(String name) {
        return personRepository.findByName(name);
    }

}