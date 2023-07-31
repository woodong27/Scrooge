package com.scrooge.scrooge.repository;

import com.scrooge.scrooge.domain.member.Member;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    @EntityGraph(attributePaths = {"mainAvatar", "mainBadge", "level"})
    Optional<Member> findWithRelatedEntitiesByEmail(String email);

    Optional<Member> findByEmail(String email);

    Optional<Member> findByNickname(String nickname);

    boolean existsMemberByEmail(String email);

    boolean existsMemberByNickname(String nickname);
}
