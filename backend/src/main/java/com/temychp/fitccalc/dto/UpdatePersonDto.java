package com.temychp.fitccalc.dto;

import com.temychp.fitccalc.util.validators.NewPasswordMatches;
import com.temychp.fitccalc.util.validators.ValidEmail;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@NewPasswordMatches
@Data
public final class UpdatePersonDto {

    private Long id;

    @NotEmpty
    private String name;

    @NotEmpty
    private String password;

    @NotEmpty
    private String oldPassword;

    @ValidEmail
    @NotEmpty
    private String email;

}