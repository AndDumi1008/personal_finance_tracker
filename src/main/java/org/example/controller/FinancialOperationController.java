package org.example.controller;

import org.example.model.FinancialOperation;
import org.example.model.lists.FinancialOperationCategory;
import org.example.model.lists.FinancialOperationCurrency;
import org.example.service.FinancialOperationService;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

@Controller
public class FinancialOperationController {

    private final FinancialOperationService financialOperationService;
    private final UserService userService;

    @Autowired
    public FinancialOperationController(FinancialOperationService financialOperationService, UserService userService) {
        this.financialOperationService = financialOperationService;
        this.userService = userService;
    }

    @GetMapping("/operation/new")
    public String showAddOperationForm(Model model) {
        model.addAttribute("operation", new FinancialOperation());
        model.addAttribute("currencies", FinancialOperationCurrency.values());
        model.addAttribute("categories", FinancialOperationCategory.values());
        return "financial-operation";
    }

    @PostMapping("/saveOperation")
    @ResponseBody
    public Map<String, Object> saveOperation(@ModelAttribute("operation") FinancialOperation operation, String details, Principal principal) {
        Map<String, Object> response = new HashMap<>();
        try {
            operation = financialOperationService.parseAndSetDetails(operation, details);
            operation = financialOperationService.setUser(operation, userService.findByUsername(principal.getName()));
            financialOperationService.saveOperation(operation);
            response.put("success", true);
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", e.getMessage());
        }
        return response;
    }

    @GetMapping("/operation/edit/{id}")
    public String editOperation(@PathVariable Long id, Model model) {
        FinancialOperation operation = financialOperationService.findById(id);
        model.addAttribute("operation", operation);
        model.addAttribute("currencies", FinancialOperationCurrency.values());
        model.addAttribute("categories", FinancialOperationCategory.values());
        return "financial-operation";
    }

}
