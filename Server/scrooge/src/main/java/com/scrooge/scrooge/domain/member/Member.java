package com.scrooge.scrooge.domain.member;

import com.scrooge.scrooge.domain.*;
import com.scrooge.scrooge.domain.community.Article;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NamedEntityGraph(name = "member.withRelatedEntities", attributeNodes = {
        @NamedAttributeNode("mainAvatar"),
        @NamedAttributeNode("mainBadge"),
        @NamedAttributeNode("level")
})
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "level_id")
    private Level level;

    @Column(length = 20, nullable = false)
    private String nickname;

    @Column(length = 255, nullable = false)
    private String email;

    @Column(length = 255, nullable = false)
    private String password;

    @Column(nullable = false)
    private String message = "상태메시지를 설정해주세요.";

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "main_avatar_id")
    private Avatar mainAvatar;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "main_badge_id")
    private Badge mainBadge;

    @Column(columnDefinition = "int default 0")
    private Integer exp;

    @Column(columnDefinition = "int default 0")
    private Integer streak;

    @Column(name = "weekly_goal", columnDefinition = "int default 0")
    private Integer weeklyGoal;

    @Column(name = "weekly_consum", columnDefinition = "int default 0")
    private Integer weeklyConsum;
    // int -> Integer 로 변경하는게 좋을 것 같다.

    @Column(name = "remain_gacha")
    private Integer remainGacha;

    @CreatedDate
    @Column(name = "joined_at")
    private LocalDateTime joinedAt;

    @Column(name = "refresh_token")
    private String refreshToken;

    @Column(name = "is_settlement_done")
    private Boolean isSettlementDone = false;

    /* 연결 */
    // 소비 내역
    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<PaymentHistory> paymentHistories = new ArrayList<>();

    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<MemberOwningAvatar> memberOwningAvatars = new ArrayList<>();

    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<MemberOwningBadge> memberOwningBadgesOwningBadges = new ArrayList<>();

    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<MemberSelectedQuest> memberSelectedQuests = new ArrayList<>();

    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<Article> articles = new ArrayList<>();

    /* refresh Token 갱신 */
    public void updateRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

}
