package com.gaurang.loanapproval.service;

import com.gaurang.loanapproval.entity.LoanApplication;
import com.gaurang.loanapproval.entity.User;
import com.gaurang.loanapproval.enums.ApplicationStatus;
import com.gaurang.loanapproval.repository.LoanApplicationRepository;
import com.gaurang.loanapproval.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class LoanService {

    private final LoanApplicationRepository loanRepo;
    private final UserRepository userRepo;
    private final CreditScoreService creditService;

    public LoanService(LoanApplicationRepository loanRepo, UserRepository userRepo, CreditScoreService creditService) {
        this.loanRepo = loanRepo;
        this.userRepo = userRepo;
        this.creditService = creditService;
    }

    //  Apply Loan
    public String applyLoan(String email,
                            Double amount,
                            Integer tenure,
                            Double income,
                            String employmentType,
                            Double existingDebt) {

        User user = userRepo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));


        //  Step 1: Calculate credit score
        var creditScore = creditService.calculateScore(user, income, existingDebt);

        //  Step 2: Decide approval
        ApplicationStatus status;

        if (creditScore.getRiskLevel() == com.gaurang.loanapproval.enums.RiskLevel.LOW) {
            status = ApplicationStatus.APPROVED;
        } else if (creditScore.getRiskLevel() == com.gaurang.loanapproval.enums.RiskLevel.HIGH) {
            status = ApplicationStatus.PENDING; // manual review
        } else {
            status = ApplicationStatus.REJECTED;
        }

        LoanApplication loan = new LoanApplication();
        loan.setUser(user);
        loan.setLoanAmount(amount);
        loan.setTenureMonths(tenure);
        loan.setIncome(income);
        loan.setEmploymentType(employmentType);
        loan.setExistingDebt(existingDebt);
        loan.setStatus(ApplicationStatus.PENDING);
        loan.setCreatedAt(LocalDateTime.now());

        loanRepo.save(loan);

        return "Loan " + status;
    }

    //  Get User Loans
    public List<LoanApplication> getUserLoans(String email) {

        User user = userRepo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return loanRepo.findByUser(user);
    }
    public List<LoanApplication> getAllApplications() {
        return loanRepo.findAll();

    }
    public String updateLoanStatus(Long id, ApplicationStatus status) {

        LoanApplication loan = loanRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Loan not found"));

        loan.setStatus(status);

        loanRepo.save(loan);

        return "Loan " + status;
    }
    
}