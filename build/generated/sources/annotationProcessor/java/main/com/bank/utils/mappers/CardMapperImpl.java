package com.bank.utils.mappers;

import com.bank.card.dto.CardDto;
import com.bank.card.entity.Card;
import java.math.BigDecimal;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-06-10T21:57:53-0500",
    comments = "version: 1.6.3, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.13.jar, environment: Java 21.0.7 (Oracle Corporation)"
)
public class CardMapperImpl implements CardMapper {

    @Override
    public Card cardDtoToCard(CardDto cardDto) {
        if ( cardDto == null ) {
            return null;
        }

        Card card = new Card();

        card.setId( cardDto.getId() );
        card.setCardType( cardDto.getCardType() );
        card.setCardNumber( cardDto.getCardNumber() );
        card.setCardName( cardDto.getCardName() );
        if ( cardDto.getValueCard() != null ) {
            card.setValueCard( BigDecimal.valueOf( cardDto.getValueCard() ) );
        }
        card.setExpiryMonth( cardDto.getExpiryMonth() );
        card.setExpiryYear( cardDto.getExpiryYear() );
        card.setCvv( cardDto.getCvv() );

        return card;
    }

    @Override
    public CardDto cardToCardDto(Card card) {
        if ( card == null ) {
            return null;
        }

        CardDto cardDto = new CardDto();

        cardDto.setId( card.getId() );
        cardDto.setCardType( card.getCardType() );
        cardDto.setCardNumber( card.getCardNumber() );
        cardDto.setCardName( card.getCardName() );
        if ( card.getValueCard() != null ) {
            cardDto.setValueCard( card.getValueCard().longValue() );
        }
        cardDto.setExpiryMonth( card.getExpiryMonth() );
        cardDto.setExpiryYear( card.getExpiryYear() );
        cardDto.setCvv( card.getCvv() );

        return cardDto;
    }
}
