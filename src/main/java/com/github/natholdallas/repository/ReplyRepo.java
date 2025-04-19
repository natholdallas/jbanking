package com.github.natholdallas.repository;

import com.github.natholdallas.model.Comment;
import com.github.natholdallas.model.Reply;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@Transactional
public interface ReplyRepo extends JpaRepository<Reply, Long> {

    Optional<Reply> findByComment(Comment comment);

}
