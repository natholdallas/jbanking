package com.github.natholdallas.controller.admin;

import cn.dev33.satoken.annotation.SaCheckLogin;
import com.github.natholdallas.controller.admin.input.ReplyCreationIn;
import com.github.natholdallas.model.Reply;
import com.github.natholdallas.repository.CommentRepo;
import com.github.natholdallas.repository.ReplyRepo;
import com.github.natholdallas.repository.UserRepo;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.OK;

@SaCheckLogin
@RequiredArgsConstructor
@RestController("admin-reply")
@RequestMapping("/admin/reply")
public final class ReplyController {

    private final UserRepo userRepo;
    private final CommentRepo commentRepo;
    private final ReplyRepo replyRepo;

    @PostMapping
    public ResponseEntity<Object> reply(@RequestBody @Valid ReplyCreationIn data) {
        var reply = new Reply(
            null,
            userRepo.cur(),
            commentRepo.findById(data.comment).get(),
            data.content
        );
        replyRepo.save(reply);
        return new ResponseEntity<>(OK);
    }

}
