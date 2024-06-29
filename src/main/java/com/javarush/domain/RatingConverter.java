package com.javarush.domain;


import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import static java.util.Objects.isNull;

@Converter(autoApply = true)
public class RatingConverter implements AttributeConverter<Rating, String> {

    @Override
    public String convertToDatabaseColumn(Rating attribute) {
        if (isNull(attribute)) {
            return null;
        }
        return attribute.getValue();
    }

    @Override
    public Rating convertToEntityAttribute(String dbData) {
        if (isNull(dbData) || dbData.isEmpty()) {
            return null;
        }

        Rating[] values = Rating.values();
        for (Rating rating : values) {
            if (rating.getValue().equals(dbData)) {
                return rating;
            }
        }

        return null;
    }
}