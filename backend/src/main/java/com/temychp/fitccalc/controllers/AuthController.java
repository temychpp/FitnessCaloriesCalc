package com.temychp.fitccalc.controllers;

import com.temychp.fitccalc.security.PersonAuthenticationProvider;
import com.temychp.fitccalc.dto.LoginDto;
import com.temychp.fitccalc.dto.PersonDto;
import com.temychp.fitccalc.dto.RegistrationDto;
import com.temychp.fitccalc.services.PersonService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@Slf4j
@RequiredArgsConstructor
@RestController
public class AuthController {

    private final PersonService personService;
    private final PersonAuthenticationProvider personAuthenticationProvider;

    @PostMapping("/login")
    public ResponseEntity<PersonDto> login(@RequestBody @Valid LoginDto loginDto) {
        PersonDto personDto = personService.login(loginDto);
        personDto.setToken(personAuthenticationProvider.createToken(personDto));
        return ResponseEntity.ok(personDto);
    }

    @PostMapping("/register")
    public ResponseEntity<PersonDto> register(@RequestBody @Valid RegistrationDto person) {
        PersonDto createdPerson = personService.register(person);
        createdPerson.setToken(personAuthenticationProvider.createToken(createdPerson));
        return ResponseEntity.created(URI.create("/person/" + createdPerson. getId())).body(createdPerson);
    }

}
