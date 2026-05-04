package com.gaurang.loanapproval.controller;

import com.gaurang.loanapproval.entity.LoanApplication;
import com.gaurang.loanapproval.service.LoanService;
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

    // ✅ Apply Loan
    @PostMapping("/apply")
    public String applyLoan(@RequestParam Double amount,
                            @RequestParam Integer tenure,
                            @RequestParam Double income,
                            @RequestParam String employmentType,
                            @RequestParam Double existingDebt) {

        String email = (String) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();

        return loanService.applyLoan(email, amount, tenure, income, employmentType, existingDebt);
    }

    // ✅ Get My Loans
    @GetMapping("/my-loans")
    public List<LoanApplication> getMyLoans() {

        String email = (String) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();

        return loanService.getUserLoans(email);
    }
}