package org.example.repository;

import org.example.model.lists.FinancialOperationTransactionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FinancialOperationTransactionTypeRepository extends JpaRepository<FinancialOperationTransactionType, Long> {
}
