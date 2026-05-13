package com.gaurang.loanapproval.dto;

public class RefreshTokenResponseDTO {

    private String accessToken;

    public RefreshTokenResponseDTO(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getAccessToken() {
        return accessToken;
    }
}