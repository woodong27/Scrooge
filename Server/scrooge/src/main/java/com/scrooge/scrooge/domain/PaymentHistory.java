package com.scrooge.scrooge.domain;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "payment_history")
@Data
@NoArgsConstructor
public class PaymentHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @CreationTimestamp
    private LocalDateTime paid_at;

    @Column(length = 20)
    private String category;

    @Column(nullable = false)
    private Integer amount;

    @Column(length = 255, nullable = false)
    private String used_at;

    @Column(length = 255)
    private String card_name;

    // 연결
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;
}
