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

    public Map<String, Double> convertToEntityAttribute(Object[] dbData) {
        Map<String, Double> categoryLimits = new HashMap<>();
        categoryLimits.put("FOOD", (Double) dbData[0]);
        categoryLimits.put("TRANSPORT", (Double) dbData[1]);
        categoryLimits.put("ENTERTAINMENT", (Double) dbData[2]);
        categoryLimits.put("UTILITIES", (Double) dbData[3]);
        categoryLimits.put("HEALTHCARE", (Double) dbData[4]);
        categoryLimits.put("EDUCATION", (Double) dbData[5]);
        categoryLimits.put("RENT", (Double) dbData[6]);
        categoryLimits.put("SALARY", (Double) dbData[7]);
        categoryLimits.put("INVESTMENT", (Double) dbData[8]);
        categoryLimits.put("OTHER", (Double) dbData[9]);
        return categoryLimits;
    }
}