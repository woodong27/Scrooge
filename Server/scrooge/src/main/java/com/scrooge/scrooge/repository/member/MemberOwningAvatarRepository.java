package com.scrooge.scrooge.repository.member;

import com.scrooge.scrooge.domain.member.MemberOwningAvatar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberOwningAvatarRepository extends JpaRepository<MemberOwningAvatar, Long> {

    List<MemberOwningAvatar> findByMemberId(Long memberId);

    boolean existsByMemberIdAndAvatarId(Long memberId, Long avatarId);

    @Query("SELECT moa FROM MemberOwningAvatar moa WHERE moa.member.id = :memberId AND moa.avatar.id = :avatarId")
    MemberOwningAvatar findByMemberIdAndAvatarId(Long memberId, Long avatarId);
}
