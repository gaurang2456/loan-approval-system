package com.gaurang.loanapproval.repository;

import com.gaurang.loanapproval.entity.CreditScore;
import com.gaurang.loanapproval.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CreditScoreRepository extends JpaRepository<CreditScore, Long> {
    Optional<CreditScore> findByUser(User user);
}