package com.temychp.fitccalc.controllers;

import com.temychp.fitccalc.services.PersonService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/calc")
public class CalculationController {

    private final PersonService personService;

    @GetMapping("/body_mass_index")
    public double getBodyMassIndex(@RequestParam(value = "id") Long id) {

        return personService.getBodyMassIndex(id);
    }

    @GetMapping("/calories_mifflin_stjeor")
    public int getSimpleCalories(@RequestParam(value = "id") Long id) {

        return personService.getCaloriesByMifflinStJeorWithActivity(id);
    }

    @GetMapping("/ideal_weight_lorenz")
    public double getLorenz(@RequestParam(value = "id") Long id) {

        return personService.getLorenz(id);
    }

    @GetMapping("/ideal_weight_devine")
    public double getDevine(@RequestParam(value = "id") Long id) {

        return personService.getDevine(id);
    }

    @GetMapping("/ideal_weight_broca")
    public double getBroca(@RequestParam(value = "id") Long id) {

        return personService.getBroca(id);
    }


}

