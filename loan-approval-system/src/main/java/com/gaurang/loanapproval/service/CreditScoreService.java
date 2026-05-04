package com.gaurang.loanapproval.service;

import com.gaurang.loanapproval.entity.CreditScore;
import com.gaurang.loanapproval.entity.User;
import com.gaurang.loanapproval.enums.RiskLevel;
import com.gaurang.loanapproval.repository.CreditScoreRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CreditScoreService {

    private final CreditScoreRepository creditRepo;

    public CreditScoreService(CreditScoreRepository creditRepo) {
        this.creditRepo = creditRepo;
    }

    public CreditScore calculateScore(User user, Double income, Double debt) {

        int score = 0;

        // 🧠 Simple logic (we can improve later)
        if (income > 5000) score += 300;
        else if (income > 3000) score += 200;
        else score += 100;

        if (debt < 1000) score += 300;
        else if (debt < 3000) score += 200;
        else score += 100;

        RiskLevel risk;
        if (score >= 500) risk = RiskLevel.LOW;
        else if (score >= 300) risk = RiskLevel.MEDIUM;
        else risk = RiskLevel.HIGH;

        CreditScore creditScore = new CreditScore();
        creditScore.setUser(user);
        creditScore.setScore(score);
        creditScore.setRiskLevel(risk);
        creditScore.setCalculatedAt(LocalDateTime.now());

        return creditRepo.save(creditScore);
    }
}