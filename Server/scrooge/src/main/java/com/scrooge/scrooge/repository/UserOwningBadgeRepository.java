package com.scrooge.scrooge.repository;

import com.scrooge.scrooge.domain.UserOwningBadge;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserOwningBadgeRepository extends JpaRepository<UserOwningBadge, Long> {
}
