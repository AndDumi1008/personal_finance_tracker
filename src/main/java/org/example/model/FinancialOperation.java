package org.example.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;
import java.util.Map;

@Getter
@Setter
@Entity
@Table(name = "financial_operations")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FinancialOperation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "operation_date")
    private Date date;

    @ElementCollection
    @CollectionTable(name = "operation_details", joinColumns = @JoinColumn(name = "operation_id"))
    @MapKeyColumn(name = "detail_key")
    @Column(name = "detail_value")
    private Map<String, String> details;

    private String currency;
    private double amount;

    private String location;

    private String category;

//    private String subcategory;

    private boolean isRecurrent;

    @Column(name = "customer_notes")
    private String customerNotes;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserModel user;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;


    public String getFormattedDetails() {
        StringBuilder formattedDetails = new StringBuilder();
        for (Map.Entry<String, String> entry : details.entrySet()) {
            formattedDetails.append(entry.getKey()).append(": ").append(entry.getValue()).append("<br>");
        }
        return formattedDetails.toString();
    }
}