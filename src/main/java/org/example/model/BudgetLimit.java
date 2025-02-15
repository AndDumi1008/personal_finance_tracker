package org.example.model;

import jakarta.persistence.*;
import lombok.*;
import org.example.util.MapToStringConverter;

import java.util.Map;

@Entity
@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "CUSTOMER_BUDGET_LIMITS")
public class BudgetLimit {
    @Id
    private Long customerId;

    @Transient
    private Map<String, Double> categoryLimits;

    @PostPersist
    public void loadCategoryLimits() {
        MapToStringConverter converter = new MapToStringConverter();
        this.categoryLimits = converter.convertToEntityAttribute(new Object[]{
                this.food, this.transport, this.entertainment, this.utilities, this.healthcare,
                this.education, this.rent, this.salary, this.investment, this.other
        });
    }

    @Column(name = "FOOD")
    private double food;
    @Column(name = "TRANSPORT")
    private double transport;
    @Column(name = "ENTERTAINMENT")
    private double entertainment;
    @Column(name = "UTILITIES")
    private double utilities;
    @Column(name = "HEALTHCARE")
    private double healthcare;
    @Column(name = "EDUCATION")
    private double education;
    @Column(name = "RENT")
    private double rent;
    @Column(name = "SALARY")
    private double salary;
    @Column(name = "INVESTMENT")
    private double investment;
    @Column(name = "OTHER")
    private double other;
}
