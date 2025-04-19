package com.github.natholdallas.repository;

import com.github.natholdallas.model.Transfer;
import com.github.natholdallas.model.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface TransferRepo extends JpaRepository<Transfer, Long> {

    List<Transfer> findAllByCardUser(User user);

    Optional<Transfer> findByIdAndCardUser(Long id, User user);

    void deleteByIdAndCardUser(Long id, User user);

    default void deleteAllByIdAndCardUser(List<Long> ids, User user) {
        ids.forEach(id -> deleteByIdAndCardUser(id, user));
    }

}
