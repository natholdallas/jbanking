package com.github.natholdallas.controller.admin.input;

import com.github.natholdallas.common.Permission;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

public final class UserCreationIn {

    @NotNull
    @NotBlank
    @NotEmpty
    @Length(min = 4, max = 20)
    public String username;

    @NotNull
    @NotBlank
    @NotEmpty
    @Length(min = 4, max = 20)
    public String password;

    @NotNull
    public Permission permission;

}
