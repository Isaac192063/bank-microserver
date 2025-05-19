package com.bank.card.entity;

import com.bank.card.enums.TypeCard;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Data
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private TypeCard cardType;
    private String cardNumber;
    private String cardName;
    private BigDecimal valueCard;
    private String expiryMonth;
    private String expiryYear;
    private String cvv;
}
