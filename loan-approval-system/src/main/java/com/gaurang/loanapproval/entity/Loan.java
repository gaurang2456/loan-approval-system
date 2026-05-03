package com.gaurang.loanapproval.entity;

import com.gaurang.loanapproval.enums.LoanStatus;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private LoanApplication application;

    private Double approvedAmount;
    private Double interestRate;
    private Double emiAmount;

    private LocalDate startDate;
    private LocalDate endDate;

    @Enumerated(EnumType.STRING)
    private LoanStatus status;
}
