package org.example.controller;

import org.example.model.Customer;
import org.example.model.FinancialOperation;
import org.example.model.UserModel;
import org.example.model.lists.FinancialOperationCategory;
import org.example.model.lists.FinancialOperationCurrency;
import org.example.service.CustomerService;
import org.example.service.FOService;
import org.example.service.SecurityService;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class DashboardController {
    @Autowired
    private UserService userService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private FOService FOService;

    @GetMapping("/dashboard")
    public String dashboard(Model model,
                            @RequestParam(required = false, defaultValue = "") String currency,
                            @RequestParam(required = false, defaultValue = "") String category) {
        model.addAttribute("currencies", FinancialOperationCurrency.values());
        model.addAttribute("categories", FinancialOperationCategory.values());
        if (securityService.isAuthenticated()) {
            UserModel user = userService.getUser(securityService.getAuthenticatedUsername());
            try {
                Customer customer = customerService.getCustomer(user.getId());
                List<FinancialOperation> operations;
                if (!category.isEmpty() || !currency.isEmpty()) {
                    operations = FOService.filterOperations(user.getId(), currency, category);

                } else {
                    operations = userService.getUserOperations(user.getUsername());
                }
                model.addAttribute("operations", operations);
                model.addAttribute("username", customer.getFirstName());
            } catch (Exception e) {
                model.addAttribute("error", "An error occurred while retrieving user operations.");
            }
        } else {
            model.addAttribute("error", "User not authenticated");
        }
        return "dashboard";
    }

}
