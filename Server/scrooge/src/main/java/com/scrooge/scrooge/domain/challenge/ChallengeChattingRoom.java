package com.scrooge.scrooge.domain.challenge;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import com.scrooge.scrooge.domain.member.Member;

@Entity
@Table(name = "challenge_chatting_room")
@Data
@NoArgsConstructor
public class ChallengeChattingRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /* TEAM */
    @Column
    Integer team;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "challenge_id")
    private Challenge challenge;

    @OneToMany(mappedBy = "challengeChattingRoom", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private List<ChallengeChattingMessage> challengeChattingMessageList = new ArrayList<>();

    @OneToMany(mappedBy = "challengeChattingRoom", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private List<ChallengeParticipant> challengeParticipantList = new ArrayList<>();
}
