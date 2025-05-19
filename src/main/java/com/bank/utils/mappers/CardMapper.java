package com.bank.utils.mappers;

import com.bank.card.dto.CardDto;
import com.bank.card.entity.Card;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CardMapper {
    CardMapper INSTANCE = Mappers.getMapper(CardMapper.class);

    Card cardDtoToCard(CardDto cardDto);
    CardDto cardToCardDto(Card card);
}
