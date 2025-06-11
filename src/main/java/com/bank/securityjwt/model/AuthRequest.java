package com.bank.securityjwt.model;

import lombok.Data;

@Data
public class AuthRequest {
    private String username;
    private String password;
}