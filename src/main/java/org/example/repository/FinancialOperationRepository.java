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

    List<FinancialOperation> findByUserIdAndCurrencyAndCategory(Long userId, String currency, String category);

    @Query("SELECT f FROM FinancialOperation f WHERE f.user.id = :userId AND f.currency = :currency AND f.deleted = false")
    List<FinancialOperation> findByUserIdAndCurrency(@Param("userId") Long userId, @Param("currency") String currency);

    @Query("SELECT f FROM FinancialOperation f WHERE f.user.id = :userId AND f.category = :category AND f.deleted = false")
    List<FinancialOperation> findByUserIdAndCategory(@Param("userId") Long userId, @Param("category") String category);

}