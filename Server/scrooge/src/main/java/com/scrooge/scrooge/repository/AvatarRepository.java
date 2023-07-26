package com.scrooge.scrooge.repository;

import com.scrooge.scrooge.domain.Avatar;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AvatarRepository extends JpaRepository<Avatar, Long> {
}
