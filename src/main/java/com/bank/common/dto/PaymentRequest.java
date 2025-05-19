package com.bank.common.dto;

import com.bank.payment.dto.CardDto;
import com.bank.user.dto.UserDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import java.math.BigDecimal;


@Data
public class PaymentRequest {

    @NotNull(message = "The payment value must not be null.")
    @Positive(message = "The payment value must be a positive number.")
    private BigDecimal value;

    @Valid
    @NotNull(message = "User information must not be null.")
    private UserDTO user;

    @Valid
    @NotNull(message = "Card info must not be blank.")
    private CardDto card;



}
