package org.example.controller;

import org.example.model.FinancialOperation;
import org.example.model.UserModel;
import org.example.model.lists.FinancialOperationCategory;
import org.example.model.lists.FinancialOperationCurrency;
import org.example.service.FinancialOperationService;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class FinancialOperationController {

    @Autowired
    private FinancialOperationService financialOperationService;

    @Autowired
    private UserService userService;

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
            // Parse the details field
            operation = financialOperationService.parseAndSetDetails(operation, details);

            // Set the user field
            operation = financialOperationService.setUser(operation, userService.findByUsername(principal.getName()));

            financialOperationService.saveOperation(operation);
            response.put("success", true);
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", e.getMessage());
        }
        return response;
    }

}
