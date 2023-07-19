package com.temychp.fitccalc.dto;

import com.temychp.fitccalc.models.person.Role;
import com.temychp.fitccalc.util.validators.PasswordMatches;
import com.temychp.fitccalc.util.validators.ValidEmail;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@PasswordMatches
@Data
public final class RegistrationDto {

    @NotEmpty
    private String name;

    @NotEmpty
    private String password;

    @NotEmpty
    private String confirmPassword;

    @ValidEmail
    @NotEmpty
    private String email;

    private Role role;

}