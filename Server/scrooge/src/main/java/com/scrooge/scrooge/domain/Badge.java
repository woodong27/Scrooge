package com.scrooge.scrooge.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Badge {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "badge_name", nullable = false, length = 20)
    private String badgeName;

    @Column(name = "badge_description", nullable = false, length = 255)
    private String badgeDescription;

    @Column(name = "img_address", nullable = false, length = 255)
    private String imgAddress;

    @Column(name = "max_count")
    private Integer maxCount;
}
