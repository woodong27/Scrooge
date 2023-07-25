package com.scrooge.scrooge.user.repository;

import com.scrooge.scrooge.user.domain.Avatar;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AvatarRepository extends JpaRepository<Avatar, Long> {
}
