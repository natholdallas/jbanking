package com.github.natholdallas.controller.admin.input;

import com.github.natholdallas.annotation.RequireNumber;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import java.math.BigDecimal;

public final class CardUpdateIn {

    @RequireNumber
    @NotNull
    @Length(min = 18, max = 18)
    public String number;

    @NotNull
    @Length(max = 10)
    public String name;

    @NotNull
    @Digits(integer = 6, fraction = 2)
    public BigDecimal balance;

}
