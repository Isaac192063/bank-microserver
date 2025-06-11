package com.bank.securityjwt.controller;

import com.bank.securityjwt.model.AuthRequest;
import com.bank.securityjwt.util.JwtUtil;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final JwtUtil jwtUtil;

    public AuthController(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/token")
    public String getToken(@RequestBody AuthRequest request) {
        if ("admin".equals(request.getUsername()) && "admin".equals(request.getPassword())) {
            return jwtUtil.generateToken(request.getUsername());
        } else {
            throw new RuntimeException("Credenciales inválidas");
        }
    }
}