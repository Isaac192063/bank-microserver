package com.bank.card.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@RequiredArgsConstructor
@Getter
@ToString
public enum TypeCard {
    VISA("Visa"),
    MASTERCARD("MasterCard"),
    AMEX("American Express"),
    DISCOVER("Discover");
    private final String displayName;
}
