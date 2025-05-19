package com.bank.payment.entity;

import com.bank.user.entity.UserEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "AUDIT_TRANSACTION")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuditTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "usr_id")
    private UserEntity user;
    private LocalDateTime date;
    private String operation;
    private String result;

    @ManyToOne
    @JoinColumn(name = "tsn_id")
    private TransactionEntity transaction;
}
