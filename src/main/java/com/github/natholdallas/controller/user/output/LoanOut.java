package com.github.natholdallas.controller.user.output;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.github.natholdallas.model.Loan;

import java.math.BigDecimal;

import static com.fasterxml.jackson.annotation.JsonFormat.Shape.STRING;

public record LoanOut(
    long id,
    CardOut card,
    @JsonFormat(shape = STRING) BigDecimal amount,
    boolean active
) {
    public static LoanOut of(Loan loan) {
        return new LoanOut(
            loan.id,
            CardOut.of(loan.card),
            loan.amount,
            loan.active
        );
    }
}
