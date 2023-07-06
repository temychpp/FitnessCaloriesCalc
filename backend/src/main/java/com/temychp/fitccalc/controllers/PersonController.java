package com.temychp.fitccalc.controllers;

import com.temychp.fitccalc.dto.PersonActivityDto;
import com.temychp.fitccalc.dto.PersonAnthropometryDto;
import com.temychp.fitccalc.dto.PersonDto;
import com.temychp.fitccalc.models.person.Person;
import com.temychp.fitccalc.services.PersonService;
import com.temychp.fitccalc.util.convertors.PersonActivityConvertor;
import com.temychp.fitccalc.util.convertors.PersonAnthroConvertor;
import com.temychp.fitccalc.util.convertors.PersonConvertor;
import com.temychp.fitccalc.util.exceptions.PersonDuplicateException;
import com.temychp.fitccalc.util.exceptions.PersonNotFoundException;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/person")
public class PersonController {

    private final PersonService personService;

    private final PersonConvertor personConvertor;

    private final PersonAnthroConvertor personAnthroConvertor;

    private final PersonActivityConvertor personActivityConvertor;


    @PostMapping
    public ResponseEntity<PersonDto> create(@RequestBody @Valid PersonDto personDto) {
        ResponseEntity<PersonDto> result;
        try {
            personService.save(personConvertor.DtoToModel(personDto));
            result = ResponseEntity.status(HttpStatus.OK).build();
            log.info("Пользователь сохранен в базу: {}", personDto);

        } catch (PersonDuplicateException e) {
            result = ResponseEntity.status(HttpStatus.CONFLICT).body(personDto);
            log.info("Пользователь уже есть в базе {}", e.getMessage());

        } catch (PersonNotFoundException e) {
            result = ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            log.info("Ошибка в запросе!{}", e.getMessage());

        } catch (Exception e) {
            result = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            log.error("Ошибка сервера! {}", e.getMessage());
        }
        return result;
    }

    @GetMapping("/{id}")
    public PersonDto getPerson(@PathVariable("id") Long id) {
        return personConvertor.ModelToDto(personService.findOne(id));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<HttpStatus> update(@Valid PersonDto personDto,
                                             @PathVariable("id") Long id) {
        personService.update(id, personConvertor.DtoToModel(personDto));
        log.info("Обновляем персональные данные пользователя с id={}", id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/anthro")
    public PersonAnthropometryDto getAnthro(@RequestParam(value = "id") Long id) {
        return personAnthroConvertor.ModelToDto(personService.findOne(id).getPersonAnthropometry());
    }

    @PostMapping("/anthro")
    public ResponseEntity<PersonAnthropometryDto> updateAnthro(
            @RequestBody PersonAnthropometryDto personAnthropometryDto) {
        ResponseEntity<PersonAnthropometryDto> result;
        try {
            Long id= personAnthropometryDto.getId();
            Person person = personService.findOne(id);
            person.setPersonAnthropometry(personAnthroConvertor.DtoToModel(personAnthropometryDto));
            personService.save(person);
            result = ResponseEntity.status(HttpStatus.OK).build();
            log.info("Антропометрия пользователя{} сохранена в базу: {}", id, personAnthropometryDto);

        } catch (Exception e) {
            result = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            log.error("Ошибка сервера! {}", e.getMessage());
        }
        return result;
    }

    @GetMapping("/activity")
    public PersonActivityDto getActivity(@RequestParam(value = "id") Long id) {
        return personActivityConvertor.ModelToDto(personService.findOne(id).getPersonActivity());
    }

    @PostMapping("/activity")
    public ResponseEntity<PersonActivityDto> updateActivity(
            @RequestBody PersonActivityDto personActivityDto) {
        ResponseEntity<PersonActivityDto> result;
        try {
            Long id= personActivityDto.getId();
            Person person = personService.findOne(id);
            person.setPersonActivity(personActivityConvertor.DtoToModel(personActivityDto));
            personService.save(person);
            result = ResponseEntity.status(HttpStatus.OK).build();
            log.info("Активность пользователя{} сохранена в базу: {}", id, personActivityDto);

        } catch (Exception e) {
            result = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            log.error("Ошибка сервера! {}", e.getMessage());
        }
        return result;
    }
}