package com.github.natholdallas.controller.user;

import cn.dev33.satoken.annotation.SaCheckLogin;
import com.github.natholdallas.controller.user.input.LoanCreationIn;
import com.github.natholdallas.model.Loan;
import com.github.natholdallas.repository.CardRepo;
import com.github.natholdallas.repository.LoanRepo;
import com.github.natholdallas.repository.UserRepo;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@SaCheckLogin
@RequiredArgsConstructor
@RestController("user-loan")
@RequestMapping("/loan")
public final class LoanController {

    private final UserRepo userRepo;
    private final LoanRepo loanRepo;
    private final CardRepo cardRepo;

    @GetMapping
    public ResponseEntity<List<Loan>> list() {
        var loans = loanRepo.findAllByCardUser(userRepo.cur());
        return new ResponseEntity<>(loans, OK);
    }

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody @Valid LoanCreationIn data) {
        var loan = new Loan(
            null,
            cardRepo.findByIdAndUser(data.card, userRepo.cur()).get(),
            data.amount,
            false
        );
        loanRepo.save(loan);
        return new ResponseEntity<>(OK);
    }

    @DeleteMapping
    public ResponseEntity<Object> destroyBatch(@RequestBody @Valid List<Long> ids) {
        loanRepo.deleteAllById(ids);
        return new ResponseEntity<>(OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> destroy(@PathVariable Long id) {
        loanRepo.deleteById(id);
        return new ResponseEntity<>(OK);
    }

}
