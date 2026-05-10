package com.gaurang.loanapproval.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LoanResponseDTO {

    private String message;

    private Long loanId;

    private String status;

    private String riskLevel;
}
