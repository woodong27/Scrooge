package com.scrooge.scrooge.repository;

import com.scrooge.scrooge.domain.MemberOwningBadge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberOwningBadgeRepository extends JpaRepository<MemberOwningBadge, Long> {

    @Query("SELECT mob FROM MemberOwningBadge mob WHERE mob.member.id = ?1")
    List<MemberOwningBadge> findMemberOwningBadgesById(Long memberId);
}
