package com.github.natholdallas.repository;

import com.github.natholdallas.model.Comment;
import com.github.natholdallas.model.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface CommentRepo extends JpaRepository<Comment, Long> {

    List<Comment> findAllByUser(User user);

    Optional<Comment> findByIdAndUser(Long id, User user);

    void deleteByIdAndUser(Long id, User user);

    default void deleteAllByIdAndUser(List<Long> ids, User user) {
        ids.forEach(id -> deleteByIdAndUser(id, user));
    }

}
