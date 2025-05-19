package com.bank.payment.service.imp;

import com.bank.card.entity.Card;
import com.bank.card.repository.CardRepository;
import com.bank.common.dto.PaymentRequest;
import com.bank.common.enums.MESSAGE_EXCEPTION_PAYMENT;
import com.bank.common.enums.STATUS_PAYMENT;
import com.bank.common.exceptions.BadRequest;
import com.bank.common.exceptions.InsufficientMoney;
import com.bank.common.exceptions.NotFoundException;
import com.bank.payment.dto.CardDto;
import com.bank.payment.entity.TransactionEntity;
import com.bank.payment.repository.TransactionRepository;
import com.bank.payment.service.interfaces.PaymentService;
import com.bank.user.entity.UserEntity;
import com.bank.user.service.interfaces.UserService;
import com.bank.utils.mappers.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PaymentServiceImp implements PaymentService {

    private final CardRepository cardRepository;
    private final TransactionRepository transactionRepository;
    private final UserService userService;

    @Override
    public TransactionEntity makePayment(PaymentRequest paymentDto) {
        TransactionEntity transactionEntity = this.buildPendingTransaction(paymentDto);

        UserEntity user = userService.createUser(UserMapper.INSTANCE.userDtoTOUserEntity(paymentDto.getUser()));
        if (user == null) {
            throw new BadRequest(MESSAGE_EXCEPTION_PAYMENT.REPEAT_EMAIL.getName());
        }
        transactionEntity.setUser(user);

//        Validar numero de tarjeta
        Card card = this.validateCard(paymentDto.getCard());
        if (card == null) {
            throw new NotFoundException(MESSAGE_EXCEPTION_PAYMENT.NOT_FOUND_CARD.getName());
        }
        transactionEntity.setCard(card);

        BigDecimal valuePay = card.getValueCard().subtract(paymentDto.getValue());
        boolean hasSufficientFunds = this.hasSufficientFunds(valuePay);

        if (!hasSufficientFunds) {
            transactionEntity.setStatusPayment(STATUS_PAYMENT.FAILED);
            transactionEntity.setDescription("Dinero insuficiente");
            TransactionEntity transaction = transactionRepository.save(transactionEntity);
            throw new InsufficientMoney(MESSAGE_EXCEPTION_PAYMENT.INVALID_BALANCE.getName(), transaction);
        }

//        Actualizar el saldo de la tarrjeta de credito
        card.setValueCard(valuePay);
        return this.saveWithStatus(transactionEntity);
    }

    private Card validateCard(CardDto card) {
        Optional<Card> cardOptional = cardRepository.findByCardNumber(card.getCardNumber());
        System.out.println(cardOptional);
        if (cardOptional.isEmpty()) {
            throw new NotFoundException(MESSAGE_EXCEPTION_PAYMENT.NOT_FOUND_CARD.getName());
        }
        Card cardEntity = cardOptional.get();
        boolean isValidateCard = cardEntity.getExpiryYear().equals(card.getExpiryYear())
                && cardEntity.getExpiryMonth().equals(card.getExpiryMonth())
                && cardEntity.getCvv().equals(card.getCvv());

        System.out.println(cardEntity);
        System.out.println(card);


        if (isValidateCard) return cardEntity;

        return null;
    }

    private TransactionEntity buildPendingTransaction(PaymentRequest paymentRequest) {
        System.out.println(paymentRequest.getUser());
        UserEntity user = UserMapper.INSTANCE.userDtoTOUserEntity(paymentRequest.getUser());
        System.out.println(user);
        return TransactionEntity.builder()
                .amount(paymentRequest.getValue())
                .dateTime(LocalDateTime.now())
                .statusPayment(STATUS_PAYMENT.WAITING)
                .user(user)
                .build();
    }

    private boolean hasSufficientFunds(BigDecimal valuePay) {
        return valuePay.compareTo(BigDecimal.ZERO) >= 0;
    }

    private TransactionEntity saveWithStatus(TransactionEntity transactionEntity) {
        transactionEntity.setStatusPayment(STATUS_PAYMENT.SUCCESS);
        transactionEntity.setDescription("Pago realizado con exito");
        return transactionRepository.save(transactionEntity);
    }

    @Override
    public TransactionEntity getPaymentById(Long id) {
        Optional<TransactionEntity> transactionEntity = this.transactionRepository.findById(id);
        if (transactionEntity.isEmpty()) {
            throw new NotFoundException(MESSAGE_EXCEPTION_PAYMENT.NOT_FOUND_TRANSACTION.getName());
        }
        return transactionEntity.get();
    }

    @Override
    public List<TransactionEntity> getAllPayment() {
        return transactionRepository.findAll();
    }

    @Override
    public List<TransactionEntity> getAllPaymentByUserDocument(String document) {
        return transactionRepository.findByUserDocument(document);
    }


}
