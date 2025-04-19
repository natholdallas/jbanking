package com.github.natholdallas.common;

import com.github.natholdallas.model.*;
import com.github.natholdallas.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
@RequiredArgsConstructor
public final class InitSchedule implements ApplicationRunner {

    private final UserRepo userRepo;
    private final CardRepo cardRepo;
    private final CommentRepo commentRepo;
    private final LoanRepo loanRepo;
    private final TransferRepo transferRepo;
    private final ReplyRepo replyRepo;

    @Override
    public void run(ApplicationArguments args) {
        // user
        userRepo.saveAll(List.of(
            new User(null, "Olga", "5662211", "Olga", "314507872100", Permission.USER),
            new User(null, "Steven", "5361550", "Steven", "838007208426", Permission.USER),
            new User(null, "Ella", "95823906", "Ella", "183146673255", Permission.USER),
            new User(null, "May", "16003732", "May", "752356146463", Permission.USER)));
        // admin
        userRepo.saveAll(List.of(
            new User(null, "Anthony", "7022948", "Anthony", "768533962314", Permission.ADMIN),
            new User(null, "Shane", "64608602", "Shane", "262707422255", Permission.ADMIN),
            new User(null, "Jeremiah", "9540749", "Jeremiah", "820439276752", Permission.ADMIN),
            new User(null, "Lola", "9459865", "Lola", "99031539402", Permission.ADMIN),
            new User(null, "Susan", "21280222", "Susan", "885403545108", Permission.ADMIN),
            new User(null, "Craig", "49627058", "Craig", "229930464877", Permission.ADMIN),
            new User(null, "Lulu", "51100663", "Lulu", "604508232791", Permission.ADMIN)));
        // insane
        var user = new User(null, "admin", "1234", "insane", "128351825485", Permission.ADMIN);
        // card
        var card1 = new Card(null, user, "12312578195154971", "浦发银行", new BigDecimal("6000"));
        var card2 = new Card(null, user, "12312578195154972", "农业银行", new BigDecimal("3000"));
        var card3 = new Card(null, user, "12312578195154973", "光大银行", new BigDecimal("200"));
        var card4 = new Card(null, user, "12312578195154974", "工商银行", new BigDecimal("100"));
        // comment
        var comment1 = new Comment(null, user, "#10d6f5", "paid oil surrounded twenty separate", false, null);
        var comment2 = new Comment(null, user, "#f15370", "extra guard fuel pitch taste herd none", false, null);
        var comment3 = new Comment(null, user, "#b5df64", "pictured table walk fog let wonderful", false, null);
        var comment4 = new Comment(null, user, "#47a9d8", "grabbed congress teeth silent death car ", false, null);
        var comment5 = new Comment(null, user, "#517972", "coast smoke find development food arrow ", false, null);
        // loan
        var loan1 = new Loan(null, card1, new BigDecimal("2000"), false);
        var loan2 = new Loan(null, card2, new BigDecimal("1500"), false);
        var loan3 = new Loan(null, card3, new BigDecimal("300"), false);
        var loan4 = new Loan(null, card4, new BigDecimal("600"), false);
        // transfer
        var transfer1 = new Transfer(null, card1, "123757812578512874", new BigDecimal("2000"));
        var transfer2 = new Transfer(null, card2, "123757812578512874", new BigDecimal("2000"));
        var transfer3 = new Transfer(null, card3, "123757812578512874", new BigDecimal("2000"));
        var transfer4 = new Transfer(null, card4, "123757812578512874", new BigDecimal("2000"));
        // reply
        var reply1 = new Reply(null, user, comment1, "你好, 我是林某某");
        var reply2 = new Reply(null, user, comment2, "你好, 我是张某某");
        var reply3 = new Reply(null, user, comment3, "你好, 我是孙某某");
        var reply4 = new Reply(null, user, comment4, "你好, 我是上某某");
        var reply5 = new Reply(null, user, comment5, "你好, 我是啊某某");
        userRepo.save(user);
        cardRepo.saveAll(List.of(card1, card2, card3, card4));
        commentRepo.saveAll(List.of(comment1, comment2, comment3, comment4, comment5));
        loanRepo.saveAll(List.of(loan1, loan2, loan3, loan4));
        transferRepo.saveAll(List.of(transfer1, transfer2, transfer3, transfer4));
        replyRepo.saveAll(List.of(reply1, reply2, reply3, reply4, reply5));
    }

}
