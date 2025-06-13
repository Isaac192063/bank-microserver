package com.bank.user.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class AccountEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name_app", nullable = false)
    private String nameApp;
    private String email;
    private String description;
    private String token;
}
