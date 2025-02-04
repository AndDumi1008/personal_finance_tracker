package org.example.service;

import org.example.model.FinancialOperation;
import org.example.model.UserModel;
import org.example.repository.FORepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class FOService {

    private final FORepository financialOperationRepository;

    @Autowired
    public FOService(FORepository financialOperationRepository) {
        this.financialOperationRepository = financialOperationRepository;
    }

    public List<FinancialOperation> findByUserId(Long userId) {
        return financialOperationRepository.findByUserId(userId);
    }

    public void saveOperation(FinancialOperation operation) {
        financialOperationRepository.save(operation);
    }

    public FinancialOperation parseAndSetDetails(FinancialOperation operation, String details) {
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

    public FinancialOperation findById(Long id) {
        return financialOperationRepository.findById(id).orElse(null);
    }


    public void markAsRemoved(Long id, Long userId) {
        FinancialOperation operation = financialOperationRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Operation not found"));
        if (!operation.getUser().getId().equals(userId)) {
            throw new IllegalArgumentException("User does not have permission to modify this operation");
        }
        operation.setDeleted(true);
        financialOperationRepository.save(operation);
    }

    public String reverseParseAndSetDetails(FinancialOperation operation) {
        return operation.getParsedDetails().entrySet().stream()
                .map(entry -> entry.getKey() + ": " + entry.getValue())
                .collect(Collectors.joining("\n"));
    }

    public List<FinancialOperation> filterOperations(Long userId, String currency, String category) {
        if(!currency.isEmpty() && !category.isEmpty()) {
            return financialOperationRepository.findByUserIdAndCurrencyAndCategory(userId, currency, category);
        }
        if(!currency.isEmpty() ) {
            return financialOperationRepository.findByUserIdAndCurrency(userId, currency);
        }
        if(!category.isEmpty()) {
            return financialOperationRepository.findByUserIdAndCategory(userId, category);
        }
        return null;
    }
}