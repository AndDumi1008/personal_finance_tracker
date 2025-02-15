package org.example.controller;

import org.example.model.RegisterUser;
import org.example.model.UserModel;
import org.example.service.AuthService;
import org.example.service.CustomerService;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistrationController {

    @Autowired
    private AuthService authService;

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("userModel", new RegisterUser());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(RegisterUser user, Model model) {
        authService.registerUser(user);
        return "redirect:/login";
    }
}