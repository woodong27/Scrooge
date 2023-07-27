package com.scrooge.scrooge.repository;

import com.scrooge.scrooge.domain.UserOwningBadge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserOwningBadgeRepository extends JpaRepository<UserOwningBadge, Long> {

    @Query("SELECT uob FROM UserOwningBadge uob WHERE uob.user.id = ?1")
    List<UserOwningBadge> findUserOwningBadgesById(Long userId);
}
