    package com.scrooge.scrooge.domain.challenge;

    import com.scrooge.scrooge.domain.member.Member;
    import lombok.Data;
    import lombok.NoArgsConstructor;

    import javax.persistence.*;
    import java.util.ArrayList;
    import java.util.List;

    @Entity
    @Table(name = "challenge_participant")
    @Data
    @NoArgsConstructor
    public class ChallengeParticipant {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column
        private Integer team;

        // 연결
        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "member_id")
        private Member member;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "challenge_id")
        private Challenge challenge;

        @OneToMany(mappedBy = "challengeParticipant", cascade = CascadeType.REMOVE)
        private List<ChallengeAuth> challengeAuths = new ArrayList<>();

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "challenge_chatting_room_id")
        private ChallengeChattingRoom challengeChattingRoom;

    }
