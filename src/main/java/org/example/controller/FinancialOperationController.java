package org.example.controller;

import org.example.model.FinancialOperation;
import org.example.model.lists.FinancialOperationCategory;
import org.example.model.lists.FinancialOperationCurrency;
import org.example.service.FOService;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

@Controller
public class FinancialOperationController {

    private final FOService FOService;
    private final UserService userService;

    @Autowired
    public FinancialOperationController(FOService FOService, UserService userService) {
        this.FOService = FOService;
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
            operation = FOService.parseAndSetDetails(operation, details);
            operation = FOService.setUser(operation, userService.findByUsername(principal.getName()));
            FOService.saveOperation(operation);
            response.put("success", true);
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", e.getMessage());
        }
        return response;
    }

    @GetMapping("/operation/edit/{id}")
    public String editOperation(@PathVariable Long id, Model model) {
        FinancialOperation operation = FOService.findById(id);
        String details = FOService.reverseParseAndSetDetails(operation);
        model.addAttribute("operation", operation);
        model.addAttribute("details", details);
        model.addAttribute("currencies", FinancialOperationCurrency.values());
        model.addAttribute("categories", FinancialOperationCategory.values());
        return "financial-operation";
    }

    @PostMapping("/markAsRemoved")
    @ResponseBody
    public Map<String, Object> markAsRemoved(@RequestParam Long id, @AuthenticationPrincipal UserDetails userDetails) {
        Map<String, Object> response = new HashMap<>();
        try {
            Long userId = userService.findByUsername(userDetails.getUsername()).getId();
            FOService.markAsRemoved(id, userId);
            response.put("success", true);
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", e.getMessage());
        }
        return response;
    }

}
