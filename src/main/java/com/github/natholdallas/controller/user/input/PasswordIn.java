package com.github.natholdallas.controller.user.input;

import com.github.natholdallas.data.output.ErrorVo;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

public record PasswordIn(
    @NotNull
    @NotEmpty
    @NotBlank
    @Length(max = 20)
    String password,
    String confirmPassword
) {

    public PasswordIn {
        if (!confirmPassword.equals(password)) {
            ErrorVo.throwError(
                new ErrorVo("ConfirmPassword", "确认密码与密码不一致")
            );
        }
    }

}
