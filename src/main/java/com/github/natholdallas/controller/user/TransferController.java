package com.github.natholdallas.controller.user;

import cn.dev33.satoken.annotation.SaCheckLogin;
import com.github.natholdallas.controller.user.input.TransferCreationIn;
import com.github.natholdallas.model.Transfer;
import com.github.natholdallas.repository.CardRepo;
import com.github.natholdallas.repository.TransferRepo;
import com.github.natholdallas.repository.UserRepo;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@SaCheckLogin
@RequiredArgsConstructor
@RestController("user-transfer")
@RequestMapping("/transfer")
public final class TransferController {

    private final UserRepo userRepo;
    private final TransferRepo transferRepo;
    private final CardRepo cardRepo;

    @GetMapping
    public ResponseEntity<List<Transfer>> list() {
        var transfers = transferRepo.findAllByCardUser(userRepo.cur());
        return new ResponseEntity<>(transfers, OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Transfer> retrieve(@PathVariable Long id) {
        var transfer = transferRepo.findByIdAndCardUser(id, userRepo.cur()).get();
        return new ResponseEntity<>(transfer, OK);
    }

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody @Valid TransferCreationIn data) {
        var transfer = new Transfer(
            null,
            cardRepo.findByIdAndUser(data.card, userRepo.cur()).get(),
            data.target,
            data.amount
        );
        transferRepo.save(transfer);
        return new ResponseEntity<>(OK);
    }

    @DeleteMapping
    public ResponseEntity<Object> destroyBatch(@RequestBody @Valid List<Long> ids) {
        transferRepo.deleteAllByIdAndCardUser(ids, userRepo.cur());
        return new ResponseEntity<>(OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> destroy(@PathVariable Long id) {
        transferRepo.deleteByIdAndCardUser(id, userRepo.cur());
        return new ResponseEntity<>(OK);
    }

}
