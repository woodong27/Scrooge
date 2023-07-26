package com.scrooge.scrooge.repository;

import com.scrooge.scrooge.domain.UserOwningAvatar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserOwningAvatarRepository extends JpaRepository<UserOwningAvatar, Long> {

    @Query("SELECT uoa FROM UserOwningAvatar uoa WHERE uoa.user.id = ?1 AND uoa.isMainAvatar = true")
    Optional<UserOwningAvatar> findMainAvatarByUserId(Long userId);
}
