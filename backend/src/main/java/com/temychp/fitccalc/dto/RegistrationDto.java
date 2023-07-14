package com.temychp.fitccalc.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public final class RegistrationDto {

    @NotEmpty
    private String name;

    @NotEmpty
    private String password;

    @NotEmpty
    private String confirmPassword;

    @NotEmpty
    private String email;

}