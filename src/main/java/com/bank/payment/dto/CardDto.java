package com.bank.payment.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class CardDto {

    @NotBlank(message = "Card number must not be blank")
    @Pattern(regexp = "^(\\d{4} ?){4}$", message = "Card number must contain 16 digits, optionally separated by spaces")
    private String cardNumber;

    @NotBlank(message = "Expiry month must not be blank")
    @Pattern(regexp = "\\d+", message = "Expiry month must contain only digits")
    private String expiryMonth;

    @NotBlank(message = "Expiry year must not be blank")
    @Pattern(regexp = "\\d+", message = "Expiry year must contain only digits")
    private String expiryYear;

    @NotBlank(message = "CVV must not be blank")
    @Pattern(regexp = "\\d+", message = "CVV must contain only digits")
    private String cvv;
}
