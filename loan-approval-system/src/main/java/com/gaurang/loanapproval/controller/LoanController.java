package com.gaurang.loanapproval.controller;

import com.gaurang.loanapproval.dto.LoanRequestDTO;
import com.gaurang.loanapproval.dto.LoanResponseDTO;
import com.gaurang.loanapproval.entity.LoanApplication;
import com.gaurang.loanapproval.enums.ApplicationStatus;
import com.gaurang.loanapproval.service.LoanService;
import jakarta.validation.Valid;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/loan")
public class LoanController {

    private final LoanService loanService;

    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    //  Apply Loan
    @PostMapping("/apply")
    public LoanResponseDTO applyLoan(@Valid @RequestBody LoanRequestDTO request) {

        String email = (String) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();

        return loanService.applyLoan(
                email,
                request.getAmount(),
                request.getTenure(),
                request.getIncome(),
                request.getEmploymentType(),
                request.getExistingDebt()
        );
    }

    //  Get My Loans
    @GetMapping("/my-loans")
    public List<LoanApplication> getMyLoans() {

        String email = (String) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();

        return loanService.getUserLoans(email);
    }
    @PostMapping("/admin/approve/{id}")
    public String approveLoan(@PathVariable Long id) {
        return loanService.updateLoanStatus(id, ApplicationStatus.APPROVED);
    }

    @PostMapping("/admin/reject/{id}")
    public String rejectLoan(@PathVariable Long id) {
        return loanService.updateLoanStatus(id, ApplicationStatus.REJECTED);
    }

    @GetMapping("/admin/all")
    public List<LoanApplication> getAllApplications() {
        return loanService.getAllApplications();
    }
}