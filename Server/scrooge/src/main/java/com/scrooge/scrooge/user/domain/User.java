package com.scrooge.scrooge.user.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany
    @JoinColumn
    private Level level;

    private String name;
    private String nickname;
    private String email;
    private String password;
    private int exp;
    private int streak;
    private int weekly_goal;
    private int weekly_consum;

    @CreatedDate
    private LocalDateTime joined_at;
}
