package com.scrooge.scrooge.domain.challenge;

import com.scrooge.scrooge.domain.Member;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "challenge")
@Data
@NoArgsConstructor
public class Challenge {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 256, nullable = false)
    private String title;

    @Column(length = 64, nullable = false)
    private String category;

    @Column(nullable = false, name = "min_participants")
    private Integer minParticipants;

    @Column(columnDefinition = "INTEGER default 20", name = "max_participants")
    private Integer maxParticipants;

    @Column(columnDefinition = "TEXT", nullable = false, name = "auth_method")
    private String authMethod; //인증 방법 소개

    @Column(columnDefinition = "TEXT", nullable = false)
    private String description; //챌린지 소개

    @Column(length = 64, nullable = false)
    private String period; //챌린지 기간

    @Column(columnDefinition = "INTEGER default 0")
    private Integer status; //상태 (0: 시작 전, 1: 진행 중, 2: 종료)

    @Column(name = "start_date")
    private LocalDateTime startDate;

    @Column(name = "end_date")
    private LocalDateTime endDate;

    // 다른 엔티티와 연결
    @ManyToOne
    @JoinColumn(name = "challenge_master")
    private Member challengeMaster;

    @OneToMany(mappedBy = "challenge", cascade = CascadeType.REMOVE)
    private List<ChallengeExampleImage> challengeExampleImageList = new ArrayList<>();
}
