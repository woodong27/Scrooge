package com.scrooge.scrooge.repository;

import com.scrooge.scrooge.domain.Avatar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AvatarRepository extends JpaRepository<Avatar, Long> {

    @Query(value = "SELECT * FROM avatar ORDER BY RAND() LIMIT 1", nativeQuery = true)
    Avatar findRandomAvatar();

}
