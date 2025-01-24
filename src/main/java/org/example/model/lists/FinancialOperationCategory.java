package org.example.model.lists;

public enum FinancialOperationCategory {
    FOOD, TRANSPORT, ENTERTAINMENT, UTILITIES, HEALTHCARE, EDUCATION, RENT, SALARY, INVESTMENT, OTHER;

    public String getDisplayName() {
        String name = this.name().toLowerCase();
        return name.substring(0, 1).toUpperCase() + name.substring(1);
    }
}
