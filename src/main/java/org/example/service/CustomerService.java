package org.example.service;

import org.example.model.Customer;
import org.example.model.RegisterUser;
import org.example.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public void save(Customer customer) {
        customerRepository.save(customer);
    }

    public Customer getCustomer(Long id) {

        Customer returnedCustomer = customerRepository.findByAccountId(id);
        return returnedCustomer;
    }

    public Customer createCustomer(RegisterUser user, Long userId) {
        Customer customer = Customer.builder()
                .accountId(userId)
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .accountId(userId)
                .creation_date(new Date())
                .build();
        save(customer);
        return customer;
    }


}
