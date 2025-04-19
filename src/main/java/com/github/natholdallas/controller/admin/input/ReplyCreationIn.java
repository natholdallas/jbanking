package com.github.natholdallas.controller.admin.input;

import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

public final class ReplyCreationIn {

    @NotNull
    public Long comment;

    @NotNull
    @Length(max = 50)
    public String content;

}
