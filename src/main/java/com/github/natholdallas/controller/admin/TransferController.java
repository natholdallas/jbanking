package com.github.natholdallas.controller.admin;

import cn.dev33.satoken.annotation.SaCheckLogin;
import com.github.natholdallas.controller.admin.input.TransferUpdateIn;
import com.github.natholdallas.controller.admin.output.TransferOut;
import com.github.natholdallas.model.Transfer;
import com.github.natholdallas.repository.CardRepo;
import com.github.natholdallas.repository.TransferRepo;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@SaCheckLogin
@RequiredArgsConstructor
@RestController("admin-transfer")
@RequestMapping("/admin/transfer")
public final class TransferController {

    private final TransferRepo transferRepo;
    private final CardRepo cardRepo;

    @GetMapping
    public ResponseEntity<List<TransferOut>> list() {
        var transfers = transferRepo.findAll()
            .stream()
            .map(TransferOut::of)
            .toList();
        return new ResponseEntity<>(transfers, OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransferOut> retrieve(@PathVariable Long id) {
        var transfer = TransferOut.of(transferRepo.findById(id).get());
        return new ResponseEntity<>(transfer, OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable Long id, @RequestBody @Valid TransferUpdateIn data) {
        var transfer = transferRepo.findById(id).get();
        transfer.card = cardRepo.findById(id).get();
        transfer.target = data.target;
        transfer.amount = data.amount;
        transferRepo.save(transfer);
        return new ResponseEntity<>(OK);
    }

    @DeleteMapping
    public ResponseEntity<Object> destroyBatch(@RequestBody List<Long> ids) {
        transferRepo.deleteAllById(ids);
        return new ResponseEntity<>(OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> destroy(@PathVariable Long id) {
        transferRepo.deleteById(id);
        return new ResponseEntity<>(OK);
    }

}
