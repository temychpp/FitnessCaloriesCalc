package com.temychp.fitccalc.services;

import com.temychp.fitccalc.configs.RestPersonSenderConfiguration;
import com.temychp.fitccalc.dto.PersonDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@AllArgsConstructor
@Slf4j
@Service
public class RestPersonSender implements PersonSenderService {

    private final static RestTemplate restTemplate = new RestTemplate();

    private final RestPersonSenderConfiguration configuration;

    @Override
    public ResponseEntity<HttpStatus> sendPersonToFront(PersonDto personDto) {
        try {
            ResponseEntity<HttpStatus> entity = restTemplate.postForEntity(configuration.getUrlPerson(), personDto, HttpStatus.class);
            if (entity.getStatusCode() != HttpStatus.OK) {
                return entity;
            }
            log.info("{} personDto успешно отправлен на фронт!", personDto);
            return ResponseEntity.ok(HttpStatus.OK);
        } catch (Exception e) {
            log.error("ОШИБКА! {}", e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }
}