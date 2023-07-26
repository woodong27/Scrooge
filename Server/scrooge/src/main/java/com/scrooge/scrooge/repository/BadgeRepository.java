package com.scrooge.scrooge.repository;

import com.scrooge.scrooge.domain.Badge;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BadgeRepository extends JpaRepository<Badge, Long> {
}
