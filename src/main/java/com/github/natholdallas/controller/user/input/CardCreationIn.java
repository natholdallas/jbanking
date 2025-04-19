package com.github.natholdallas.controller.user.input;

import com.github.natholdallas.annotation.RequireNumber;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

public final class CardCreationIn {

    @RequireNumber
    @NotNull
    @Length(min = 18, max = 18)
    public String number;

    @NotNull
    @Length(max = 10)
    public String name;

}
