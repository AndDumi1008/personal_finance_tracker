package org.example.service;

import org.example.model.FinancialOperation;
import org.example.model.UserModel;
import org.example.repository.FinancialOperationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    public void saveOperation(FinancialOperation operation) {
        financialOperationRepository.save(operation);
    }

    public FinancialOperation parseAndSetDetails(FinancialOperation operation,String details) {
        Map<String, String> parsedDetails = Arrays.stream(details.split("\n"))
                .map(line -> line.split(":"))
                .filter(parts -> parts.length == 2)
                .collect(Collectors.toMap(parts -> parts[0].trim(), parts -> parts[1].trim()));
        operation.setParsedDetails(parsedDetails);
        return operation;
    }

    public FinancialOperation setUser(FinancialOperation operation, UserModel user) {
        operation.setUser(user);
        return operation;
    }
}