package com.temychp.fitccalc.services;

import com.temychp.fitccalc.dto.PersonDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public interface PersonSenderService {

    ResponseEntity<HttpStatus> sendPersonToFront(PersonDto personDto);

}