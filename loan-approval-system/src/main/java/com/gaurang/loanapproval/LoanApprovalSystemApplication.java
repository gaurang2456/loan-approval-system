package com.gaurang.loanapproval;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching

@SpringBootApplication
public class LoanApprovalSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoanApprovalSystemApplication.class, args);
	}

}
