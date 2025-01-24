package org.example.service;

import org.example.model.*;
import org.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements UserDetailsService {

    private UserRepository userRepository;
    private CustomerService customerService;
    private FinancialOperationService financialOperationService;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, CustomerService customerService, FinancialOperationService financialOperationService) {
        this.userRepository = userRepository;
        this.customerService = customerService;
        this.financialOperationService = financialOperationService;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserModel byLogin = findByUsername(username);
        if (byLogin == null) {
            return null;
        }
        return User.builder()
                .username(byLogin.getUsername())
                .password(byLogin.getPassword())
                .roles(byLogin.getRole().name())
                .build();
    }

    public void register(RegisterUser registerUser) {
        UserModel userModel = UserModel.builder()
                .username(registerUser.getUsername())
                .password(passwordEncoder.encode(registerUser.getPassword()))
                .email(registerUser.getEmail())
                .role(UserRole.USER)
                .build();
        if (findByUsername(userModel.getUsername()) != null) {
            throw new IllegalArgumentException("User already exists");
        }
        UserModel createdUserModel = userRepository.save(userModel);
        Customer customer = Customer.builder()
                .firstName(registerUser.getFirstName())
                .lastName(registerUser.getLastName())
                .accountId(createdUserModel.getId())
                .build();
        // Add logic to save the customer to the database
        customerService.save(customer);

    }

    public UserModel findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public boolean verifyPassword(UserModel userModel, String pass) {
        String encodedPass = passwordEncoder.encode(pass);
        return userRepository.findByUsername(userModel.getUsername()).getPassword().equals(encodedPass);
    }

    public List<FinancialOperation> getUserOperations(String username) {
        UserModel user = findByUsername(username);
        if (user == null) {
            throw new IllegalArgumentException("User not found");
        }
        return financialOperationService.findByUserId(user.getId());
    }

}