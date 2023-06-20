package com.temychp.fitccalc.configs;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Getter
@Configuration
public class RestPersonSenderConfiguration {

    @Value("${front.urlPerson}")
    private String urlPerson;

    @Value("${front.urlProduct}")
    private String urlProduct;

}