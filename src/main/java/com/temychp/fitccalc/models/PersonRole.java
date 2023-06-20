package com.temychp.fitccalc.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PersonRole {

    ADMIN("admin"),

    USER("user");

    private final String personRole;

}