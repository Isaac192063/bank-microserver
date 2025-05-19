package com.bank.payment.controller;

import com.bank.common.dto.PaymentRequest;
import com.bank.payment.entity.TransactionEntity;
import com.bank.payment.service.interfaces.PaymentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/payment")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping()
    public ResponseEntity<TransactionEntity> makePayment(@RequestBody @Valid PaymentRequest paymentRequest) {
        return ResponseEntity.ok(paymentService.makePayment(paymentRequest));
    }

    @GetMapping("{id}")
    public ResponseEntity<TransactionEntity> getTransactionById(@PathVariable Long id) {
        return ResponseEntity.ok(
                paymentService.getPaymentById(id)
        );
    }

    @GetMapping("user/{document}")
    public ResponseEntity<List<TransactionEntity>> getTransactionByUserDocument(@PathVariable String document) {
        return ResponseEntity.ok(
                paymentService.getAllPaymentByUserDocument(document)
        );
    }
}
