package com.scrooge.scrooge.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "user_owning_avatar")
@Getter @Setter
@NoArgsConstructor
public class UserOwningAvatar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "acquired_at")
    @CreatedDate
    LocalDateTime acquiredAt; //습득 날짜

//    @Column(name = "is_main_avatar")
//    private Boolean isMainAvatar = false; //대표이미지 여부

    // 연결
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "avatar_id")
    private Avatar avatar;
}
