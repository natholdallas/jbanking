package com.github.natholdallas.controller.admin;

import cn.dev33.satoken.annotation.SaCheckLogin;
import com.github.natholdallas.controller.admin.output.LoanOut;
import com.github.natholdallas.repository.CardRepo;
import com.github.natholdallas.repository.LoanRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@SaCheckLogin
@RequiredArgsConstructor
@RestController("admin-loan")
@RequestMapping("/admin/loan")
public final class LoanController {

    private final LoanRepo loanRepo;
    private final CardRepo cardRepo;

    @GetMapping
    public ResponseEntity<List<LoanOut>> list() {
        var loans = loanRepo.findAll()
            .stream()
            .map(LoanOut::of)
            .toList();
        return new ResponseEntity<>(loans, OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LoanOut> retrieve(@PathVariable Long id) {
        var loan = LoanOut.of(loanRepo.findById(id).get());
        return new ResponseEntity<>(loan, OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Object> activeLoan(@PathVariable Long id) {
        var loan = loanRepo.findById(id).get();
        loan.active = true;
        loan.card.balance = loan.card.balance.add(loan.amount);
        loanRepo.save(loan);
        return new ResponseEntity<>(OK);
    }

    @DeleteMapping
    public ResponseEntity<Object> destroyBatch(@RequestBody List<Long> ids) {
        loanRepo.deleteAllById(ids);
        return new ResponseEntity<>(OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> destroy(@PathVariable Long id) {
        loanRepo.deleteById(id);
        return new ResponseEntity<>(OK);
    }

}
