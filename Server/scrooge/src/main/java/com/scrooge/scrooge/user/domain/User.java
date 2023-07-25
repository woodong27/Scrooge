package com.scrooge.scrooge.user.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 255, nullable = false)
    private String email;

    @Column(length = 20, nullable = false)
    private String name;

    @Column(length = 20, nullable = false)
    private String nickname;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "level_id")
    private Level level;

    @Column(length = 255, nullable = false)
    @JsonIgnore
    private String password;

    @Column(nullable = false)
    private int exp;

    @Column(nullable = false)
    private int streak;

    @Column(nullable = false)
    private int weekly_goal;

    @Column(nullable = false)
    private int weekly_consum;

    @CreatedDate
    private LocalDateTime joined_at;

    /* 연결 */

    // 사용자가 소유한 아바타 목록
    @OneToMany(mappedBy = "user")
    private List<UserOwningAvatar> userOwningAvatars = new ArrayList<>();

    // 소비 내역
    @OneToMany(mappedBy = "user")
    private List<PaymentHistory> paymentHistories = new ArrayList<>();
}
