package com.scrooge.scrooge.domain;

import com.scrooge.scrooge.domain.member.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "member_owning_avatar")
@Getter @Setter
@NoArgsConstructor
public class MemberOwningAvatar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "acquired_at")
    @CreatedDate
    LocalDateTime acquiredAt; //습득 날짜

    // 연결
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "avatar_id")
    private Avatar avatar;
}
