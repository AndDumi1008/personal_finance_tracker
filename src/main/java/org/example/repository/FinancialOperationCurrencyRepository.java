package org.example.repository;

import org.example.model.lists.FinancialOperationCurrency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FinancialOperationCurrencyRepository extends JpaRepository<FinancialOperationCurrency, Long> {
}
