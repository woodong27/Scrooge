package com.scrooge.scrooge.user.repository;

import com.scrooge.scrooge.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUserId(Long id);
}
