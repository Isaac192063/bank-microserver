package com.bank.card.service.interfaces;

import com.bank.card.dto.CardDto;

import java.util.List;

public interface CardService {
    CardDto createCard(CardDto cardDto);
    List<CardDto> getAllCards();
    void deleteCardById(Long id);
}
