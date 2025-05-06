package com.bini.Fraud_Detection.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String phoneNumber;
    private Double amount;
    private LocalDateTime timestamp;

    private boolean fraudulent;

    // Getters and Setters
}
