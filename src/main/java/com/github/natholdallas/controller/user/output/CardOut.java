package com.github.natholdallas.controller.user.output;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.github.natholdallas.model.Card;

import java.math.BigDecimal;

import static com.fasterxml.jackson.annotation.JsonFormat.Shape.STRING;

public record CardOut(
    long id,
    String number,
    String name,
    @JsonFormat(shape = STRING) BigDecimal balance
) {
    public static CardOut of(Card card) {
        return new CardOut(
            card.id,
            card.number,
            card.name,
            card.balance
        );
    }
}
