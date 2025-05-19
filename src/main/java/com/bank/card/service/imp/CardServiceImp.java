package com.bank.card.service.imp;

import com.bank.card.dto.CardDto;
import com.bank.card.entity.Card;
import com.bank.card.repository.CardRepository;
import com.bank.card.service.interfaces.CardService;
import com.bank.utils.mappers.CardMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CardServiceImp implements CardService {

    private final CardRepository cardRepository;

    @Override
    public CardDto createCard(CardDto cardDto) {
        Card card = CardMapper.INSTANCE.cardDtoToCard(cardDto);

        return CardMapper.INSTANCE.cardToCardDto(cardRepository.save(card));
    }

    @Override
    public List<CardDto> getAllCards() {
        return cardRepository.findAll().stream().map(CardMapper.INSTANCE::cardToCardDto).toList();
    }

    @Override
    public void deleteCardById(Long id) {

    }
}
