package org.example.model;

import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import org.example.util.MapToStringConverter;

import java.util.Map;

@Entity
@Builder
@Table(name = "CUSTOMER_BUDGET_LIMITS")
public class BudgetLimit {

    @Id
    private Long id;

    @Convert(converter = MapToStringConverter.class)
    private Map<String, Double> categoryLimits;

}
