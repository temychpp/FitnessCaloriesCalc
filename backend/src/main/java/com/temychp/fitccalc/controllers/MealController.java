package com.temychp.fitccalc.controllers;

import com.temychp.fitccalc.dto.PersonProductByDayDto;
import com.temychp.fitccalc.util.convertors.PersonProductByDayConvertor;
import com.temychp.fitccalc.util.exceptions.AppException;
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
@RequestMapping("/meal")
public class MealController {

    private final PersonProductByDayConvertor personProductByDayConvertor;

    @PostMapping("/mealbyday")
    public ResponseEntity<?> createMealByDay(@RequestBody @Valid PersonProductByDayDto personProductByDayDto) {
        ResponseEntity<?> result;
        try {

            personProductByDayConvertor.dtoToModel(personProductByDayDto);
            result = ResponseEntity.status(HttpStatus.OK).build();
            log.info("Еда за день пользователя сохранена в базу: {}", personProductByDayDto);
        } catch (AppException e) {
            result = ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            log.info("Ошибка в запросе!{}", e.getMessage());
        } catch (Exception e) {
            result = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            log.error("Ошибка сервера! {}", e.getMessage());
        }
        return result;
    }


}