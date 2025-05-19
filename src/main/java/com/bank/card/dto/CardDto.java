package com.bank.card.dto;

import com.bank.card.enums.TypeCard;
import com.bank.user.entity.UserEntity;
import lombok.Data;

@Data
public class CardDto {
    private TypeCard cardType;
    private String cardNumber;
    private String cardName;
    private Long valueCard;
    private String expiryMonth;
    private String expiryYear;
    private String cvv;
}
