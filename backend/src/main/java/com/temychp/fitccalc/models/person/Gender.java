package com.temychp.fitccalc.models.person;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Gender {

    MALE("male"),

    FEMALE("female"),

    LGBTQIA("other");

    private final String personGender;

}