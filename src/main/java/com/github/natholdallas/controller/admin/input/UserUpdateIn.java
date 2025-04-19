package com.github.natholdallas.controller.admin.input;

import com.github.natholdallas.common.Permission;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

public final class UserUpdateIn {

    @NotNull
    @NotBlank
    @NotEmpty
    @Length(min = 4, max = 20)
    public String username;

    @NotNull
    @NotEmpty
    @NotBlank
    @Length(max = 10)
    public String idName;

    @NotNull
    @NotEmpty
    @NotBlank
    @Length(min = 18, max = 18)
    public String idCard;

    @NotNull
    public Permission permission;

}
