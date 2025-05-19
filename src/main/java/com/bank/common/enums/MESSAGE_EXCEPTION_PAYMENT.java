package com.bank.common.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum MESSAGE_EXCEPTION_PAYMENT {
    NOT_FOUND_CARD("no se encontro la tarjeta de credito"),
    NOT_FOUND_TRANSACTION("no se encontro la transacción buscada"),
    INVALID_CARD("la tarjeta de credito no es valida"),
    REPEAT_EMAIL("Email repetido"),
    INVALID_BALANCE("saldo insuficiente");

    private final String name;
}
