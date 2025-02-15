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

    public void saveBudgetSettings(Map<String, String> budgetSettings, Long customerId) {
        Map<String, Double> categoryLimits = budgetSettings.entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> {
                            try {
                                return Double.valueOf(entry.getValue());
                            } catch (NumberFormatException e) {
                                return 0.0;
                            }
                        }
                ));

        BudgetLimit budgetLimit = BudgetLimit.builder()
                .customerId(customerId)
                .food(categoryLimits.getOrDefault("FOOD", 0.0))
                .transport(categoryLimits.getOrDefault("TRANSPORT", 0.0))
                .entertainment(categoryLimits.getOrDefault("ENTERTAINMENT", 0.0))
                .utilities(categoryLimits.getOrDefault("UTILITIES", 0.0))
                .healthcare(categoryLimits.getOrDefault("HEALTHCARE", 0.0))
                .education(categoryLimits.getOrDefault("EDUCATION", 0.0))
                .rent(categoryLimits.getOrDefault("RENT", 0.0))
                .salary(categoryLimits.getOrDefault("SALARY", 0.0))
                .investment(categoryLimits.getOrDefault("INVESTMENT", 0.0))
                .other(categoryLimits.getOrDefault("OTHER", 0.0))
                .build();

        budgetLimitRepository.save(budgetLimit);
    }

    public BudgetLimit getBudgetLimitByCustomerId(Long customerId) {
        return budgetLimitRepository.findByCustomerId(customerId);
    }
}