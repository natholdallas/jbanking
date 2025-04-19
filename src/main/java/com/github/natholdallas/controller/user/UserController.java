package com.github.natholdallas.controller.user;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.stp.StpUtil;
import com.github.natholdallas.controller.user.input.IdentityIn;
import com.github.natholdallas.controller.user.input.PasswordIn;
import com.github.natholdallas.controller.user.output.UserOut;
import com.github.natholdallas.repository.UserRepo;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.OK;

@SaCheckLogin
@RequiredArgsConstructor
@RestController("user")
@RequestMapping("/user")
public final class UserController {

    private final UserRepo userRepo;

    @GetMapping
    public ResponseEntity<UserOut> retrieve() {
        var user = UserOut.of(userRepo.cur());
        return new ResponseEntity<>(user, OK);
    }

    @PatchMapping("/identity")
    public ResponseEntity<Object> patchIdentity(@RequestBody @Valid IdentityIn data) {
        var user = userRepo.cur();
        user.idName = data.idName;
        user.idCard = data.idCard;
        userRepo.save(user);
        return new ResponseEntity<>(OK);
    }

    @PatchMapping("/password")
    public ResponseEntity<Object> patchPassword(@RequestBody @Valid PasswordIn data) {
        var user = userRepo.cur();
        user.password = data.password();
        userRepo.save(user);
        return new ResponseEntity<>(OK);
    }

    @DeleteMapping
    public ResponseEntity<Object> destroy() {
        StpUtil.logout();
        userRepo.deleteById(StpUtil.getLoginIdAsLong());
        return new ResponseEntity<>(OK);
    }

}
