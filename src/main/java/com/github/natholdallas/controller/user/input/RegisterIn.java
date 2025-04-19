package com.github.natholdallas.controller.user.input;

import com.github.natholdallas.data.output.ErrorVo;
import com.github.natholdallas.repository.UserRepo;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import static com.github.natholdallas.Main.context;

public record RegisterIn(
    @NotNull
    @NotBlank
    @NotEmpty
    @Length(min = 4, max = 20)
    String username,
    @NotNull
    @NotBlank
    @NotEmpty
    @Length(min = 4, max = 20)
    String password
) {

    public RegisterIn {
        var userRepo = context.getBean(UserRepo.class);
        if (userRepo.findByUsername(username).isPresent()) {
            ErrorVo.throwError(
                new ErrorVo("已被注册", "账号已被注册")
            );
        }
    }

}