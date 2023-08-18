package com.scrooge.scrooge.repository.member;

import com.scrooge.scrooge.domain.member.MemberOwningBadge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberOwningBadgeRepository extends JpaRepository<MemberOwningBadge, Long> {

    List<MemberOwningBadge> findByMemberId(Long memberId);

    boolean existsByBadgeIdAndMemberId(Long badgeId, Long memberId);

    boolean existsByMemberId(Long memberId);
}
