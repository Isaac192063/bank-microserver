package com.bank.common.exceptions;

import com.bank.payment.entity.TransactionEntity;
import lombok.Getter;

@Getter
public class InsufficientMoney extends RuntimeException {

    private final TransactionEntity transaction;

    public InsufficientMoney(String message, TransactionEntity transaction) {
        super(message);
        this.transaction = transaction;
    }
}
