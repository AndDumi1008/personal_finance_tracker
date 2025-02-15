package org.example.service;

import org.example.model.RegisterUser;
import org.example.model.UserModel;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final UserService userService;
    private final CustomerService customerService;
    private final SecurityService securityService;

    public AuthService(UserService userService, CustomerService customerService, SecurityService securityService) {
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
}
