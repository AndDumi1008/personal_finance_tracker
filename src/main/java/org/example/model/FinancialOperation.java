package org.example.model;

import jakarta.persistence.*;
import lombok.*;
import org.example.model.lists.FinancialOperationTransactionType;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

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
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;

    @ElementCollection
    @CollectionTable(name = "operation_details", joinColumns = @JoinColumn(name = "operation_id"))
    @MapKeyColumn(name = "detail_key")
    @Column(name = "detail_value")
    private Map<String, String> parsedDetails;

    private String currency;
    private double amount;
    private String location;
    private String category;
    private boolean isRecurrent;

    @Column(name = "customer_notes")
    private String customerNotes;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserModel user;

    @Enumerated(EnumType.STRING)
    @Column(name = "operation_type", nullable = false)
    private FinancialOperationTransactionType operationType;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;

    @Column(name = "deleted", nullable = false)
    private boolean deleted = false;

    public String getFormattedDetails() {
        StringBuilder formattedDetails = new StringBuilder();
        for (Map.Entry<String, String> entry : parsedDetails.entrySet()) {
            formattedDetails.append(entry.getKey()).append(": ").append(entry.getValue()).append("<br>");
        }
        return formattedDetails.toString();
    }

}