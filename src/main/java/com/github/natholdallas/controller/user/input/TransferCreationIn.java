package com.github.natholdallas.controller.user.input;

import com.github.natholdallas.annotation.RequireNumber;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import java.math.BigDecimal;

public final class TransferCreationIn {

    @NotNull
    public Long card;

    @NotNull
    @NotEmpty
    @NotBlank
    @Length(max = 20)
    @RequireNumber
    public String target;

    @NotNull
    @Digits(integer = 6, fraction = 2)
    public BigDecimal amount;
}
