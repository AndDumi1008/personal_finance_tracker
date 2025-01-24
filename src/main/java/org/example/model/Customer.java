package org.example.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import java.util.Date;

@Data
@Entity
@Builder
@Table(name = "customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "account_id")
    private Long accountId;
    @CreatedDate @Column(name = "creation_date")
    private Date creation_date;
}
