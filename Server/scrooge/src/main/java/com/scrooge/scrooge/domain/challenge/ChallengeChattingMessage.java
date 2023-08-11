package com.scrooge.scrooge.domain.challenge;

import com.scrooge.scrooge.domain.member.Member;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "challenge_chatting_message")
@Data
@NoArgsConstructor
public class ChallengeChattingMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String content;

    @Column(name = "created_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdAt;

    /* 연결 */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "challenge_chatting_room_id")
    private ChallengeChattingRoom challengeChattingRoom;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "message_author")
    private Member member;
}
