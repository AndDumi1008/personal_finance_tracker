package org.example.util;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Converter
public class MapToStringConverter implements AttributeConverter<Map<String, Double>, String> {

    @Override
    public String convertToDatabaseColumn(Map<String, Double> attribute) {
        if (attribute == null || attribute.isEmpty()) {
            return "";
        }
        return attribute.entrySet().stream()
                .map(entry -> entry.getKey() + "=" + entry.getValue())
                .collect(Collectors.joining(","));
    }

    @Override
    public Map<String, Double> convertToEntityAttribute(String dbData) {
        if (dbData == null || dbData.isEmpty()) {
            return new HashMap<>();
        }
        return Stream.of(dbData.split(","))
                .map(entry -> entry.split("="))
                .collect(Collectors.toMap(entry -> entry[0], entry -> Double.valueOf(entry[1])));
    }
}