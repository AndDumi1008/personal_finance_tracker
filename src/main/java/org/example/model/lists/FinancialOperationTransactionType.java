package org.example.model.lists;

public enum FinancialOperationTransactionType {
    DEPOSIT,
    WITHDRAWAL,
    TRANSFER,
    PAYMENT;

    public String getDisplayName() {
        String name = this.name().toLowerCase();
        return name.substring(0, 1).toUpperCase() + name.substring(1);
    }
}
