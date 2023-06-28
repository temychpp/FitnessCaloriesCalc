package com.temychp.fitccalc.dto;

import com.temychp.fitccalc.models.person.PersonActivity;
import com.temychp.fitccalc.models.person.PersonAnthropometry;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Setter
@Getter
@ToString
public final class PersonDto {

    @NotEmpty(message = "Name should not be empty")
    private String name;

    @NotEmpty(message = "Email should not be empty")
    private String email;

    private PersonAnthropometry personAnthropometry;

    private PersonActivity personActivity;

}