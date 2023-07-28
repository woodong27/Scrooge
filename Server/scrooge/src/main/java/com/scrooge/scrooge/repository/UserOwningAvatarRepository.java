package com.scrooge.scrooge.repository;

import com.scrooge.scrooge.domain.UserOwningAvatar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserOwningAvatarRepository extends JpaRepository<UserOwningAvatar, Long> {

    @Query("SELECT uoa FROM UserOwningAvatar uoa WHERE uoa.user.id = ?1")
    List<UserOwningAvatar> findUserOwningAvatarsById(Long userId);
}
