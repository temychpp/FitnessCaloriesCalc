package com.temychp.fitccalc.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.temychp.fitccalc.dto.PersonDto;
import com.temychp.fitccalc.models.person.Person;
import com.temychp.fitccalc.models.person.Role;
import com.temychp.fitccalc.services.PersonService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.Collections;
import java.util.Date;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Component
public class PersonAuthenticationProvider {

    @Value("${jwt.token.secret-key:secret-key}")
    private String secretKey;

    @Value("${jwt.token.lifetime:lifetime}")
    private int jwtLifetime;

    private final PersonService personService;

    @PostConstruct
    protected void init() {
        // this is to avoid having the raw secret key available in the JVM
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
        log.info(secretKey);
    }

    public String createToken(PersonDto personDto) {

        Date issuedDate = new Date();
        Date expiredDate = new Date(issuedDate.getTime() + jwtLifetime);

        Algorithm algorithm = Algorithm.HMAC256(secretKey);

        return JWT.create()
                .withIssuer(personDto.getName())
                .withClaim("role", personDto.getRole().getPersonRole())
                .withIssuedAt(issuedDate)
                .withExpiresAt(expiredDate)
                .sign(algorithm);
    }

    public Authentication validateToken(String token) {
        Algorithm algorithm = Algorithm.HMAC256(secretKey);

        JWTVerifier verifier = JWT.require(algorithm).build();

        DecodedJWT decoded = verifier.verify(token);

        PersonDto personDto = PersonDto.builder()
                .name(decoded.getIssuer())
                .role(Role.valueOf((decoded.getClaim("role").asString())))
                .build();

        return new UsernamePasswordAuthenticationToken(personDto, null, Collections.singleton(new SimpleGrantedAuthority("ROLE_" + personDto.getRole().getPersonRole())));
    }

    public Authentication validateTokenStrongly(String token) {
        Algorithm algorithm = Algorithm.HMAC256(secretKey);

        JWTVerifier verifier = JWT.require(algorithm).build();

        DecodedJWT decoded = verifier.verify(token);

        Optional<Person> person = personService.findByName(decoded.getIssuer());

        return new UsernamePasswordAuthenticationToken(person, null, Collections.singleton(new SimpleGrantedAuthority("ROLE_" + person.get().getRole().getPersonRole())));
    }

}