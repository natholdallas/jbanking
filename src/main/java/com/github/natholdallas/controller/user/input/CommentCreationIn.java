package com.github.natholdallas.controller.user.input;

import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

public final class CommentCreationIn {

    @NotNull
    @Length(max = 20)
    public String title;

    @NotNull
    @Length(max = 50)
    public String content;

}