package com.scrooge.scrooge.domain;

import com.scrooge.scrooge.domain.member.Member;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Level {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private int level;

    @Column(nullable = false, name = "required_exp")
    private int requiredExp;

    @Column(nullable = false)
    private int gacha;

    @OneToMany(mappedBy = "level")
    private List<Member> members = new ArrayList<>();
}
