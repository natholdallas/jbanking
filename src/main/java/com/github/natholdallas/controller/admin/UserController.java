package com.github.natholdallas.controller.admin;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.stp.StpUtil;
import com.github.natholdallas.controller.admin.input.UserCreationIn;
import com.github.natholdallas.controller.admin.input.UserUpdateIn;
import com.github.natholdallas.controller.admin.output.UserOut;
import com.github.natholdallas.model.User;
import com.github.natholdallas.repository.UserRepo;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@SaCheckLogin
@RequiredArgsConstructor
@RestController("admin")
@RequestMapping("/admin")
public final class UserController {

    private final UserRepo userRepo;

    @GetMapping
    public ResponseEntity<List<UserOut>> list() {
        var users = userRepo.findAll()
            .stream()
            .map(UserOut::of)
            .toList();
        return new ResponseEntity<>(users, OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserOut> retrieve(@PathVariable Long id) {
        var user = UserOut.of(userRepo.findById(id).get());
        return new ResponseEntity<>(user, OK);
    }

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody UserCreationIn data) {
        var user = new User(
            null,
            data.username,
            data.password,
            null,
            null,
            data.permission
        );
        userRepo.save(user);
        return new ResponseEntity<>(OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable Long id, @RequestBody @Valid UserUpdateIn data) {
        var user = userRepo.findById(id).get();
        user.username = data.username;
        user.idName = data.idName;
        user.idCard = data.idCard;
        user.permission = data.permission;
        userRepo.save(user);
        return new ResponseEntity<>(OK);
    }

    @DeleteMapping
    public ResponseEntity<Object> destroy(@RequestBody List<Long> ids) {
        userRepo.deleteAllById(ids);
        if (ids.contains(StpUtil.getLoginIdAsLong())) {
            StpUtil.logout();
        }
        return new ResponseEntity<>(OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> destroy(@PathVariable Long id) {
        userRepo.deleteById(id);
        if (id == StpUtil.getLoginIdAsLong()) {
            StpUtil.logout();
        }
        return new ResponseEntity<>(OK);
    }

}
