package com.bank.common.dto;

import java.time.LocalDateTime;
import java.util.List;

public record ErrorDto(
        List<String> message,
        boolean success,
        LocalDateTime dateTime
) {
}
