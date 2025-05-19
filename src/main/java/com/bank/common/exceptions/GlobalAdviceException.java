package com.bank.common.exceptions;

import com.bank.common.dto.ErrorDto;
import com.bank.payment.entity.TransactionEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class GlobalAdviceException {


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDto> methodArgumentException(MethodArgumentNotValidException exception) {
        ArrayList<String> errors = new ArrayList<>();

        exception.getFieldErrors().stream().forEach(erro -> {
            errors.add(erro.getField() + " - " + erro.getDefaultMessage());
        });
        return new ResponseEntity<>(
                new ErrorDto(
                        errors,
                        false,
                        LocalDateTime.now()
                ),
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorDto> notFoundException(NotFoundException exception) {

        return new ResponseEntity<>(
                new ErrorDto(
                        List.of(exception.getMessage()),
                        false,
                        LocalDateTime.now()
                ),
                HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(BadRequest.class)
    public ResponseEntity<ErrorDto> notFoundException(BadRequest exception) {

        return new ResponseEntity<>(
                new ErrorDto(
                        List.of(exception.getMessage()),
                        false,
                        LocalDateTime.now()
                ),
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(InsufficientMoney.class)
    public ResponseEntity<TransactionEntity> insufficientMoneyCard(InsufficientMoney exception) {
        return new ResponseEntity<>(
               exception.getTransaction(),
                HttpStatus.PAYMENT_REQUIRED
        );
    }


}
