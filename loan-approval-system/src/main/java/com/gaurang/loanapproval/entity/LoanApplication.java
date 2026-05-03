package com.gaurang.loanapproval.entity;

import com.gaurang.loanapproval.enums.Status;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class LoanApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private Double loanAmount;
    private Integer tenureMonths;
    private Double income;
    private String employmentType;
    private Double existingDebt;

    @Enumerated(EnumType.STRING)
    private Status status;

    private LocalDateTime createdAt;
}