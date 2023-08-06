package com.scrooge.scrooge.domain.challenge;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "challenge_example_image")
@Data
@NoArgsConstructor
public class ChallengeExampleImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 256, name = "img_address")
    private String imgAddress;

    // 연결
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "challenge_id")
    private Challenge challenge;


}
