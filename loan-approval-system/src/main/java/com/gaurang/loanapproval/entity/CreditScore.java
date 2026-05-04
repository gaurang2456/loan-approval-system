package com.gaurang.loanapproval.entity;

import com.gaurang.loanapproval.enums.RiskLevel;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

@Entity
public class CreditScore {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private User user;

    private Integer score;

    @Enumerated(EnumType.STRING)
    private RiskLevel riskLevel;

    private LocalDateTime calculatedAt;
}