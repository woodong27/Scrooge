package com.scrooge.scrooge.user.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "payment_history")
@Getter @Setter
@NoArgsConstructor
public class PaymentHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime paid_at;

    @Column(length = 20, nullable = false)
    private String category;

    @Column(nullable = false)
    private int amount;

    @Column(length = 255, nullable = false)
    private String used_at;

    @Column(length = 255)
    private String card_name;

    // 연결
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;
}
