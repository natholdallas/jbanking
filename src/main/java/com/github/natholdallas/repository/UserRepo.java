package com.github.natholdallas.repository;

import cn.dev33.satoken.stp.StpUtil;
import com.github.natholdallas.model.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@Transactional
public interface UserRepo extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

    Optional<User> findByUsernameAndPassword(String username, String password);

    default User cur() {
        return findById(StpUtil.getLoginIdAsLong()).orElseThrow();
    }

}
