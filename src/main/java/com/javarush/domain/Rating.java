package com.javarush.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import static java.util.Objects.isNull;

@AllArgsConstructor
@Getter
public enum Rating {
    G("G"),
    PG("PG"),
    PG_13("PG-13"),
    R("R"),
    NC_17("NC-17");

    private final String value;
}
