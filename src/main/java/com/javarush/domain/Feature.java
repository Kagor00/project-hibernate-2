package com.javarush.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import static java.util.Objects.isNull;

@AllArgsConstructor
@Getter
public enum Feature {
    TRAILERS("Trailers"),
    COMMENTARIES("Commentaries"),
    DELETED_SCENES("Deleted scenes"),
    BEHIND_THE_SCENES("Behind the scenes");

    private final String value;

    public static Feature getFeatureByValue(String value) {
        if (isNull(value) || value.isEmpty()) {
            return null;
        }

        Feature[] features = Feature.values();

        for (Feature feature: features) {
            if (feature.value.equalsIgnoreCase(value)) {
                return feature;
            }
        }

        return null;
    }
}
