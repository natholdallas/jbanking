package com.github.natholdallas.controller.user;

import cn.dev33.satoken.annotation.SaCheckLogin;
import com.github.natholdallas.controller.user.input.CardCreationIn;
import com.github.natholdallas.controller.user.output.CardOut;
import com.github.natholdallas.model.Card;
import com.github.natholdallas.repository.CardRepo;
import com.github.natholdallas.repository.UserRepo;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@SaCheckLogin
@RequiredArgsConstructor
@RestController("user-card")
@RequestMapping("/card")
public final class CardController {

    private final UserRepo userRepo;
    private final CardRepo cardRepo;

    @GetMapping
    public ResponseEntity<List<CardOut>> list() {
        var cards = cardRepo.findAllByUser(userRepo.cur())
            .stream()
            .map(CardOut::of)
            .toList();
        return new ResponseEntity<>(cards, OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CardOut> retrieve(@PathVariable Long id) {
        var card = CardOut.of(cardRepo.findByIdAndUser(id, userRepo.cur()).get());
        return new ResponseEntity<>(card, OK);
    }

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody @Valid CardCreationIn data) {
        var card = new Card(
            null,
            userRepo.cur(),
            data.number,
            data.name,
            new BigDecimal("0.00")
        );
        cardRepo.save(card);
        return new ResponseEntity<>(OK);
    }

    @DeleteMapping
    public ResponseEntity<Object> destroyBatch(@RequestBody List<Long> ids) {
        cardRepo.deleteAllByIdAndUser(ids, userRepo.cur());
        return new ResponseEntity<>(OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> destroy(@PathVariable Long id) {
        cardRepo.deleteByIdAndUser(id, userRepo.cur());
        return new ResponseEntity<>(OK);
    }

}
