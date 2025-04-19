package com.github.natholdallas.repository;

import com.github.natholdallas.model.Card;
import com.github.natholdallas.model.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface CardRepo extends JpaRepository<Card, Long> {

    List<Card> findAllByUser(User user);

    Optional<Card> findByIdAndUser(Long card, User user);

    void deleteByIdAndUser(Long id, User user);

    default void deleteAllByIdAndUser(List<Long> ids, User user) {
        ids.forEach(id -> deleteByIdAndUser(id, user));
    }

}
