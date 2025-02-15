package org.example.service;

import org.example.model.BudgetLimit;
import org.example.repository.BudgetLimitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.stream.Collectors;

@Service
public class BudgetService {
    @Autowired
    BudgetLimitRepository budgetLimitRepository;


    public void saveBudgetSettings(Map<String, String> budgetSettings,  Long customerId) {
        Map<String, Double> categoryLimits = budgetSettings.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, entry -> Double.valueOf(entry.getValue())));

        BudgetLimit budgetLimit = BudgetLimit.builder()
                .id(customerId)
                .categoryLimits(categoryLimits)
                .build();

        budgetLimitRepository.save(budgetLimit);
    }
}
