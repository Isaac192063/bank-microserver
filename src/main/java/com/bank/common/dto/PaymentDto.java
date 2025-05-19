package com.bank.common.dto;

import com.bank.common.enums.STATUS_PAYMENT;
import com.bank.user.dto.UserDTO;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PaymentDto {
    private UserDTO user;
    private STATUS_PAYMENT statusPayment;
    private Long value;
    private LocalDateTime date;
}
