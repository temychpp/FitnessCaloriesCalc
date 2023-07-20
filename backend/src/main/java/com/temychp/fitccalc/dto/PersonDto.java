package com.temychp.fitccalc.dto;

import com.temychp.fitccalc.models.person.Role;
import com.temychp.fitccalc.util.validators.ValidEmail;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
@ToString
public final class PersonDto {

    @NotEmpty(message = "Name should not be empty")
    private String name;

    @ValidEmail
    @NotNull
    @NotEmpty(message = "Email should not be empty")
    private String email;

    private Role role;

    private String token;

}