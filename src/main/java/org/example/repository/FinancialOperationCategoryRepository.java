package org.example.repository;

import org.example.model.lists.FinancialOperationCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FinancialOperationCategoryRepository extends JpaRepository<FinancialOperationCategory, Long> {
}
