package org.example.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.example.model.RegisterUser;
import org.example.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final UserService userService;
    private final CustomerService customerService;

    @Autowired
    public AuthService(UserService userService, CustomerService customerService) {
        this.userService = userService;
        this.customerService = customerService;
    }

    public void registerUser(RegisterUser user) {
        UserModel newUser = userService.register(user);
        if (newUser != null) {
            customerService.createCustomer(user, newUser.getId());
        }
    }

    public void logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            System.out.println("Session before invalidation: " + session.getAttributeNames());
            session.invalidate();
            System.out.println("Session invalidated.");
        }
        SecurityContextHolder.clearContext();
    }
}
