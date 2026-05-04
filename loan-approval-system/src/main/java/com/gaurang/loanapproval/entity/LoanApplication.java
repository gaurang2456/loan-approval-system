package com.gaurang.loanapproval.entity;

import com.gaurang.loanapproval.enums.ApplicationStatus;
import jakarta.persistence.*;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class LoanApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    private Double loanAmount;
    private Integer tenureMonths;
    private Double income;
    private String employmentType;
    private Double existingDebt;

    @Enumerated(EnumType.STRING)
    private ApplicationStatus status;

    private LocalDateTime createdAt;
}