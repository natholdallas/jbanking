package com.github.natholdallas.controller.admin;

import cn.dev33.satoken.annotation.SaCheckLogin;
import com.github.natholdallas.controller.admin.input.CardUpdateIn;
import com.github.natholdallas.controller.admin.output.CardOut;
import com.github.natholdallas.repository.CardRepo;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@SaCheckLogin
@RequiredArgsConstructor
@RestController("admin-card")
@RequestMapping("/admin/card")
public final class CardController {

    private final CardRepo cardRepo;

    @GetMapping
    public ResponseEntity<List<CardOut>> list() {
        var cards = cardRepo.findAll()
            .stream()
            .map(CardOut::of)
            .toList();
        return new ResponseEntity<>(cards, OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CardOut> retrieve(@PathVariable Long id) {
        var card = CardOut.of(cardRepo.findById(id).get());
        return new ResponseEntity<>(card, OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable Long id, @RequestBody @Valid CardUpdateIn data) {
        var card = cardRepo.findById(id).get();
        card.number = data.number;
        card.name = data.name;
        card.balance = data.balance;
        cardRepo.save(card);
        return new ResponseEntity<>(OK);
    }

    @DeleteMapping
    public ResponseEntity<Object> destroyBatch(@RequestBody List<Long> ids) {
        cardRepo.deleteAllById(ids);
        return new ResponseEntity<>(OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> destroy(@PathVariable Long id) {
        cardRepo.deleteById(id);
        return new ResponseEntity<>(OK);
    }

}
