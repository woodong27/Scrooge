package com.scrooge.scrooge.user.repository;

import com.scrooge.scrooge.user.domain.UserOwningAvatar;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserOwningAvatarRepository extends JpaRepository<UserOwningAvatar, Long> {
}
