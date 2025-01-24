package org.example.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.example.model.UserModel;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {
    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    @PostMapping("/login")
    public String loginUser(@RequestParam String username, @RequestParam String password, Model model) {
        UserModel userModel = userService.findByUsername(username);
        if (userModel != null && userService.verifyPassword(userModel, password)) {
            return "redirect:/dashboard";
        } else {
            model.addAttribute("error", "Incorrect account");
            return "login";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            System.out.println("Session before invalidation: " + session.getAttributeNames());
            session.invalidate();
            System.out.println("Session invalidated.");
        }
        SecurityContextHolder.clearContext();
        return "redirect:/login";

    }
}