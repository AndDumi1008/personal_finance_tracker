package org.example.service;

import org.example.repository.FinancialOperationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DashboardService {

    private final FinancialOperationRepository financialOperationRepository;
    private final UserService userService;

    @Autowired
    public DashboardService(FinancialOperationRepository financialOperationRepository, UserService userService) {
        this.financialOperationRepository = financialOperationRepository;
        this.userService = userService;
    }


}