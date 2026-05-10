package com.gaurang.loanapproval.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoanRequestDTO {

    @NotNull(message = "Amount is required")
    @Min(value = 1000, message = "Loan amount must be at least 1000")
    private Double amount;

    @NotNull(message = "Tenure is required")
    @Min(value = 1, message = "Tenure must be at least 1 month")
    private Integer tenure;

    @NotNull(message = "Income is required")
    @Min(value = 1000, message = "Income must be positive")
    private Double income;

    @NotBlank(message = "Employment type is required")
    private String employmentType;

    @NotNull(message = "Existing debt is required")
    @Min(value = 0, message = "Existing debt cannot be negative")
    private Double existingDebt;
}
