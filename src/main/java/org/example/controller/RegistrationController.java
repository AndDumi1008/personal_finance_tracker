package org.example.controller;

import org.example.model.RegisterUser;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistrationController {

    @Autowired
    private UserService userService;

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("userModel", new RegisterUser());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(RegisterUser user, Model model) {
        // Save the user to the database
        userService.register(user);
        return "redirect:/login";
    }
}