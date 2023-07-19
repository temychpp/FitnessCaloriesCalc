package com.temychp.fitccalc.controllers;

import com.temychp.fitccalc.dto.PersonDto;
import com.temychp.fitccalc.dto.RegistrationDto;
import com.temychp.fitccalc.security.PersonAuthenticationProvider;
import com.temychp.fitccalc.services.PersonService;
import com.temychp.fitccalc.util.exceptions.PersonDuplicateException;
import com.temychp.fitccalc.util.exceptions.PersonNotFoundException;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/admin")
public class AdminController {

    private final PersonService personService;

    private final PersonAuthenticationProvider personAuthenticationProvider;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody @Valid RegistrationDto person) {
        ResponseEntity<?> result;
        try {
            PersonDto createdPerson = personService.register(person);
            createdPerson.setToken(personAuthenticationProvider.createToken(createdPerson));
            result = ResponseEntity.status(HttpStatus.OK).body(createdPerson);
            log.info("Пользователь сохранен в базу: {}", person);
        } catch (PersonDuplicateException e) {
            result = ResponseEntity.status(HttpStatus.CONFLICT).body(person);
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
}