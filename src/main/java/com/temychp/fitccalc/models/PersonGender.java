package com.temychp.fitccalc.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PersonGender {

    MALE("male"),

    FEMALE("female");

    private final String personGender;

}