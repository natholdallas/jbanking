package com.github.natholdallas.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.stp.StpUtil;
import com.github.natholdallas.common.Permission;
import com.github.natholdallas.controller.user.input.LoginIn;
import com.github.natholdallas.controller.user.input.RegisterIn;
import com.github.natholdallas.model.User;
import com.github.natholdallas.repository.UserRepo;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.OK;

@RequiredArgsConstructor
@RestController("global-account")
public final class AccountController {

    private final UserRepo userRepo;

    @PostMapping(path = "/register")
    public ResponseEntity<Object> register(@RequestBody @Valid RegisterIn data) {
        var user = new User(
            null,
            data.username(),
            data.password(),
            null,
            null,
            Permission.USER
        );
        userRepo.save(user);
        return new ResponseEntity<>(OK);
    }

    @PostMapping(path = "/login")
    public ResponseEntity<Object> login(@RequestBody @Valid LoginIn data) {
        var user = userRepo.findByUsernameAndPassword(data.username(), data.password()).get();
        StpUtil.login(user.id);
        return new ResponseEntity<>(OK);
    }

    @SaCheckLogin
    @PostMapping("/logout")
    public ResponseEntity<Object> logout() {
        StpUtil.logout();
        return new ResponseEntity<>(OK);
    }

}
