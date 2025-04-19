package com.github.natholdallas.controller.admin.output;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.github.natholdallas.model.Transfer;

import java.math.BigDecimal;

import static com.fasterxml.jackson.annotation.JsonFormat.Shape.STRING;

public record TransferOut(
    long id,
    CardOut card,
    String target,
    @JsonFormat(shape = STRING) BigDecimal amount
) {
    public static TransferOut of(Transfer transfer) {
        return new TransferOut(
            transfer.id,
            CardOut.of(transfer.card),
            transfer.target,
            transfer.amount
        );
    }
}
