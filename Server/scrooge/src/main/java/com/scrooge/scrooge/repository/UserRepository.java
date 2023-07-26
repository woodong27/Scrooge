package com.scrooge.scrooge.repository;

import com.scrooge.scrooge.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUserId(Long id);
}
