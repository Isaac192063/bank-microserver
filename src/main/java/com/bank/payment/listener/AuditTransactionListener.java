package com.bank.payment.listener;

import com.bank.common.enums.STATUS_PAYMENT;
import com.bank.payment.entity.AuditTransaction;
import com.bank.payment.entity.TransactionEntity;
import com.bank.payment.repository.AuditRepository;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class AuditTransactionListener {

    private final AuditRepository auditRepository;

    @PrePersist
    private void prePersist(TransactionEntity transaction) {
        String result = this.getResultToOperation(transaction.getStatusPayment());

        AuditTransaction auditTransaction = AuditTransaction.builder()
                .date(LocalDateTime.now())
                .transaction(transaction)
                .user(transaction.getUser())
                .result(result)
                .operation("POST")
                .build();

        auditRepository.save(auditTransaction);
    }

    private String getResultToOperation(STATUS_PAYMENT statusPayment) {
        if (statusPayment.equals(STATUS_PAYMENT.WAITING)) {
            return "La transacción esta en proceso";
        }
        if (statusPayment.equals(STATUS_PAYMENT.SUCCESS)) {
            return "La transacción se completo satisfactoriamente";
        }
        return "La transacción fue fallida";

    }

}
