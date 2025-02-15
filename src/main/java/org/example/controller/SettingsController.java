package org.example.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.example.model.BudgetLimit;
import org.example.model.lists.FinancialOperationCategory;
import org.example.service.AuthService;
import org.example.service.BudgetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.Map;

@Controller
public class SettingsController {

    @Autowired
    private BudgetService budgetService;

    @Autowired
    private AuthService authService;

    @GetMapping("/settings")
    public String showSettingsPage(Model model) {
        model.addAttribute("categories", FinancialOperationCategory.values());
        Long customerId = authService.getLoggedInUser().getId();
        BudgetLimit budgetLimit = budgetService.getBudgetLimitByCustomerId(customerId);

//        TODO why tf is not displaying db values
        if (budgetLimit != null) {
            model.addAttribute("budgetSettings", budgetLimit.getCategoryLimits());
        } else {
            model.addAttribute("budgetSettings", new HashMap<String, Double>());
        }

        return "settings";
    }

    @PostMapping("/settings/budget")
    public String saveBudgetSettings(@RequestParam Map<String, String> budgetSettings, Model model, HttpServletRequest request) {
        budgetSettings.remove("_csrf");
        budgetService.saveBudgetSettings(budgetSettings, authService.getLoggedInUser().getId());
        return "redirect:/settings";
    }
}