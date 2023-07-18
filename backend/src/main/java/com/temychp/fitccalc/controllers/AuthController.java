package com.temychp.fitccalc.controllers;

import com.temychp.fitccalc.dto.LoginDto;
import com.temychp.fitccalc.dto.PersonDto;
import com.temychp.fitccalc.dto.RegistrationDto;
import com.temychp.fitccalc.security.PersonAuthenticationProvider;
import com.temychp.fitccalc.services.PersonService;
import com.temychp.fitccalc.util.exceptions.PersonNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
public class AuthController {

    private final PersonService personService;
    private final PersonAuthenticationProvider personAuthenticationProvider;

    @PostMapping("/login")
    public ResponseEntity<PersonDto> login(@RequestBody @Valid LoginDto loginDto) {
        ResponseEntity<PersonDto> result;
        try {
            PersonDto personDto = personService.login(loginDto);
            log.info("loginDto={} personDto={}",loginDto,personDto);
            personDto.setToken(personAuthenticationProvider.createToken(personDto));
            log.info("Token={}", personDto.getToken());
            result = ResponseEntity.status(HttpStatus.OK).build();
        } catch (PersonNotFoundException e) {
            result = ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            log.info("Ошибка в запросе!{}", e.getMessage());
        } catch (Exception e) {
            result = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            log.error("Ошибка сервера! {}", e.getMessage());
        }
        return result;
    }

    @PostMapping("/register")
    public ResponseEntity<PersonDto> register(@RequestBody @Valid RegistrationDto person) {
        ResponseEntity<PersonDto> result;
        try {
            PersonDto createdPerson = personService.register(person);
            createdPerson.setToken(personAuthenticationProvider.createToken(createdPerson));
            result = ResponseEntity.status(HttpStatus.OK).build();
            log.info("Пользователь сохранен в базу: {}", person);
//        } catch (PersonDuplicateException e) {
//            result = ResponseEntity.status(HttpStatus.CONFLICT).body(person);
//            log.info("Пользователь уже есть в базе {}", e.getMessage());
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