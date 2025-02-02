package org.example.controller;

import org.example.model.RegisterUser;
import org.example.model.lists.FinancialOperationCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Controller
public class SettingsController {


    @GetMapping("/settings")
    public String showSettingsPage(Model model) {
//        List<FinancialOperationCategory> categories = Arrays.stream(FinancialOperationCategory.values()).toList();
        model.addAttribute("categories", FinancialOperationCategory.values());
        return "settings";
    }

    @PostMapping("/settings/budget")
    public String saveBudgetSettings(@RequestParam Map<String, String> budgetSettings, Model model) {
//        budgetService.saveBudgetSettings(budgetSettings);
        return "redirect:/settings";
    }
}
