package com.scrooge.scrooge.user.repository;

import com.scrooge.scrooge.user.domain.UserOwningAvatar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserOwningAvatarRepository extends JpaRepository<UserOwningAvatar, Long> {
}
