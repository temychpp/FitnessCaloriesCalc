package com.temychp.fitccalc.controllers;

import com.temychp.fitccalc.dto.PersonDto;
import com.temychp.fitccalc.services.PersonService;
import com.temychp.fitccalc.util.exceptions.PersonDuplicateException;

import com.temychp.fitccalc.util.convertors.PersonConvertor;
import com.temychp.fitccalc.util.exceptions.PersonNotFoundException;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/person")
public class PersonController {

    private final PersonService personService;

    private final PersonConvertor personConvertor;

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
            log.info("Ошибка сервера! {}", e.getMessage());
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
        personService.update(id,personConvertor.DtoToModel(personDto));
        log.info("Обновляем персональные данные пользователя с id={}", id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

}