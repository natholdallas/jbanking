package com.github.natholdallas.controller.user.input;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

public final class IdentityIn {

    @NotNull
    @NotEmpty
    @NotBlank
    @Length(max = 10)
    public String idName;

    @NotNull
    @NotEmpty
    @NotBlank
    @Length(max = 18)
    public String idCard;

}