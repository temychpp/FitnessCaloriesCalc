package com.temychp.fitccalc.controllers;

import com.temychp.fitccalc.services.CalculationService;
import com.temychp.fitccalc.services.PersonService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/calc")
public class CalculationController {

    private final CalculationService calculationService;

    private final PersonService personService;

    @GetMapping("/body_mass_index")
    public double getBodyMassIndex() {
        Long id = personService.getPersonIdFromContext();
        return calculationService.getBodyMassIndex(id);
    }

    @GetMapping("/calories_mifflin_stjeor")
    public int getSimpleCalories() {
        Long id = personService.getPersonIdFromContext();
        return calculationService.getCaloriesByMifflinStJeorWithActivity(id);
    }

    @GetMapping("/ideal_weight_lorenz")
    public double getLorenz() {
        Long id = personService.getPersonIdFromContext();
        return calculationService.getLorenz(id);
    }

    @GetMapping("/ideal_weight_devine")
    public double getDevine() {
        Long id = personService.getPersonIdFromContext();
        return calculationService.getDevine(id);
    }

    @GetMapping("/ideal_weight_broca")
    public double getBroca() {
        Long id = personService.getPersonIdFromContext();
        return calculationService.getBroca(id);
    }


}

