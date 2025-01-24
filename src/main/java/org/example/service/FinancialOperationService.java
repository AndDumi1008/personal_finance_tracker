package org.example.service;

import org.example.model.FinancialOperation;
import org.example.repository.FinancialOperationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FinancialOperationService {

    private final FinancialOperationRepository financialOperationRepository;

    @Autowired
    public FinancialOperationService(FinancialOperationRepository financialOperationRepository) {
        this.financialOperationRepository = financialOperationRepository;
    }

    public List<FinancialOperation> findByUserId(Long userId) {
        return financialOperationRepository.findByUserId(userId);
    }

}