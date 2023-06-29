package com.temychp.fitccalc.models.person;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Gender {

    MALE("MALE"),

    FEMALE("FEMALE"),

    LGBTQIA("OTHER");

    private final String personGender;

}