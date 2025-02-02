package org.example.repository;

import org.example.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Customer findByFirstName(String firstname);

    @Query("SELECT c FROM Customer c WHERE c.accountId = ?1")
    Customer findByAccountId(Long accountId);
}
