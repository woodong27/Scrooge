package com.scrooge.scrooge.repository;

import com.scrooge.scrooge.domain.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @EntityGraph(attributePaths = {"mainAvatar", "mainBadge", "level"})
    Optional<User> findWithRelatedEntitiesById(Long userId);
}
