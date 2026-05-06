package com.gaurang.loanapproval.repository;

import com.gaurang.loanapproval.entity.Loan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanRepository extends JpaRepository<Loan, Long> {
}