package org.example.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.example.model.RegisterUser;
import org.example.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private PasswordEncoder passwordEncoder;
    private final UserService userService;
    private final CustomerService customerService;
    private final SecurityService securityService;

    @Autowired
    public AuthService(UserService userService, CustomerService customerService, SecurityService securityService) {
        this.passwordEncoder = new BCryptPasswordEncoder();
        this.userService = userService;
        this.customerService = customerService;
        this.securityService = securityService;
    }

    public void registerUser(RegisterUser user) {
        UserModel newUser = userService.register(user);
        if (newUser != null) {
            customerService.createCustomer(user, newUser.getId());
        }
    }

    public boolean verifyPassword(UserModel userModel, String pass) {
        String encodedPass = passwordEncoder.encode(pass);
        return userService.findByUsername(userModel.getUsername()).getPassword().equals(encodedPass);
    }


    public boolean isAuthenticated() {
        return securityService.isAuthenticated();
    }

    public boolean authUser(String username, String password) {
        UserModel userModel = userService.findByUsername(username);
        if (userModel != null && verifyPassword(userModel, password)) {
            return true;
        }
        return false;
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
