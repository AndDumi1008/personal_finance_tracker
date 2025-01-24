package org.example.controller;

import org.example.model.FinancialOperation;
import org.example.service.SecurityService;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class DashboardController {
    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        if (securityService.isAuthenticated()) {
            String username = securityService.getAuthenticatedUsername();
            try {
                List<FinancialOperation> operations = userService.getUserOperations(username);
                model.addAttribute("operations", operations);
                model.addAttribute("username", username);
            } catch (Exception e) {
                model.addAttribute("error", "An error occurred while retrieving user operations.");
            }
        } else {
            model.addAttribute("error", "User not authenticated");
        }
        return "dashboard";
    }
}
