package com.bank.card.controller;

import com.bank.payment.entity.TransactionEntity;
import com.bank.payment.repository.TransactionRepository;
import com.bank.payment.service.interfaces.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/transaction")
public class TransactionController {

    private final PaymentService paymentService;

    @GetMapping("")
    public String getTransaction(Model model) {
        List<TransactionEntity> transactionEntities = paymentService.getAllPayment();
        model.addAttribute("transactions", transactionEntities);
        return "view-transaction";
    }


    @GetMapping("/user/{document}")
    public String getTransactionByDocument(Model model, @PathVariable String document) {
        return "transaction";
    }

    @GetMapping("{id}")
    public String getTransactionId(Model model, @PathVariable Long id) {
        TransactionEntity transactionEntity = paymentService.getPaymentById(id);
        model.addAttribute("transaction", transactionEntity);
        return "only-transaction";
    }

}
