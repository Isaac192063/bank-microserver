package com.bank.payment.service.interfaces;

import com.bank.common.dto.PaymentDto;
import com.bank.common.dto.PaymentRequest;
import com.bank.payment.entity.TransactionEntity;

import java.util.List;

public interface PaymentService {
    TransactionEntity makePayment(PaymentRequest paymentDto);
    TransactionEntity getPaymentById(Long id);
    List<TransactionEntity> getAllPayment();
    List<TransactionEntity> getAllPaymentByUserDocument(String document);
}
