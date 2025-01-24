package org.example.repository;

import org.example.model.FinancialOperation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FinancialOperationRepository extends JpaRepository<FinancialOperation, Long> {
    @Query("SELECT f FROM FinancialOperation f WHERE f.user.id = :userId AND f.deleted = false")
    List<FinancialOperation> findByUserId(@Param("userId") Long userId);

    @Query("SELECT f FROM FinancialOperation f WHERE f.id = :id AND f.deleted = false")
    Optional<FinancialOperation> findById(@Param("id") Long id);
}