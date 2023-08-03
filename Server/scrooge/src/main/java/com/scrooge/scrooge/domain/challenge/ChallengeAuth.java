package com.scrooge.scrooge.domain.challenge;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "challenge_auth")
@Data
@NoArgsConstructor
public class ChallengeAuth {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "created_at")
    private LocalDateTime createdAt; // 인증 날짜

    @Column(length = 255, name = "img_address")
    private String imgAddress;

    @Column(name = "is_success")
    private Boolean isSuccess; // 성공 여부 성공하면 true, 아니면 false

    /* 연결 */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "challenge_participant_id")
    private ChallengeParticipant challengeParticipant;

}
