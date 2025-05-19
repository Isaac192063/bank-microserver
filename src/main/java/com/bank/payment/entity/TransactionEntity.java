package com.bank.payment.entity;

import com.bank.card.entity.Card;
import com.bank.common.enums.STATUS_PAYMENT;
import com.bank.payment.listener.AuditTransactionListener;
import com.bank.user.entity.UserEntity;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Table(name = "TRANSACTION")
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@EntityListeners(AuditTransactionListener.class)
public class TransactionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime dateTime;
    private String description;

    @Enumerated(EnumType.STRING)
    private STATUS_PAYMENT statusPayment;

    @ManyToOne()
    @JoinColumn(name = "cad_id")
    private Card card;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "usr_id")
    private UserEntity user;
    private BigDecimal amount;

}
