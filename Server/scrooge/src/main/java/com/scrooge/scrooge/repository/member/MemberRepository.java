package com.scrooge.scrooge.repository.member;

import com.scrooge.scrooge.domain.member.Member;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    @EntityGraph(attributePaths = {"mainAvatar", "mainBadge", "level"})
    Optional<Member> findWithRelatedEntitiesByEmail(String email);

    @EntityGraph(attributePaths = {"mainAvatar", "mainBadge", "level"})
    Optional<Member> findWithRelatedEntitiesById(Long memberId);

    Optional<Member> findByEmail(String email);

    boolean existsMemberByEmail(String email);

    boolean existsMemberByNickname(String nickname);

    boolean existsByIdAndIsSettlementDone(Long memberId, boolean isSettlementDone);
}
