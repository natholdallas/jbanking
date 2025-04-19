package com.github.natholdallas.controller.user.input;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public final class LoanCreationIn {

    @NotNull
    public Long card;

    @NotNull
    @Digits(integer = 6, fraction = 2)
    public BigDecimal amount;

}