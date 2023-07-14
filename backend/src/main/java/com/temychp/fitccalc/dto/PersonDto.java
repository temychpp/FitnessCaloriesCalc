package com.temychp.fitccalc.dto;

import com.temychp.fitccalc.models.person.PersonActivity;
import com.temychp.fitccalc.models.person.PersonAnthropometry;
import com.temychp.fitccalc.models.person.Role;
import com.temychp.fitccalc.util.validators.PasswordMatches;
import com.temychp.fitccalc.util.validators.ValidEmail;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@PasswordMatches
@Setter
@Getter
@ToString
public final class PersonDto {

    private Long id;

    @NotEmpty(message = "Name should not be empty")
    private String name;

    @ValidEmail
    @NotNull
    @NotEmpty(message = "Email should not be empty")
    private String email;

    @NotNull
    @NotEmpty
    private String password;

    private String matchingPassword;

    private PersonAnthropometry personAnthropometry;

    private PersonActivity personActivity;

    private Role role;

    private String token;

}